package com.mentos.mentosandroid.util

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import android.net.Uri
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

class MultiPartResolver(private val context: Context) {

    fun createImgMultiPart(uri: Uri): MultipartBody.Part {
        val options = BitmapFactory.Options()
        val inputStream = context.contentResolver.openInputStream(uri)
        val byteArrayOutputStream = ByteArrayOutputStream()
        getRotatedBitmap(
            BitmapFactory.decodeStream(inputStream, null, options),
            getOrientationOfImage(uri)
        )?.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
        val file = File(replaceFileName(uri.toString()))
        val surveyBody = RequestBody.create(
            MediaType.parse("image/jpg"),
            byteArrayOutputStream.toByteArray()
        )
        return MultipartBody.Part.createFormData("imageFile", file.name, surveyBody)
    }

    private fun getOrientationOfImage(uri: Uri): Int {
        val exif: ExifInterface?
        try {
            val filePath = getPathFromUri(uri)
            exif = ExifInterface(filePath)
        } catch (e: IOException) {
            e.printStackTrace()
            return -1
        }
        val orientation: Int = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)
        if (orientation != -1) {
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> return 90
                ExifInterface.ORIENTATION_ROTATE_180 -> return 180
                ExifInterface.ORIENTATION_ROTATE_270 -> return 270
            }
        }
        return 0
    }

    @Throws(Exception::class)
    fun getRotatedBitmap(bitmap: Bitmap?, degrees: Int): Bitmap? {
        if (bitmap == null) return null
        if (degrees == 0) return bitmap
        val matrix = Matrix()
        matrix.postRotate(degrees.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    private fun getPathFromUri(uri: Uri): String {
        val cursor: Cursor = context.contentResolver.query(uri, null, null, null, null)
            ?: throw NullPointerException()
        cursor.moveToNext()
        val columnIndex = cursor.getColumnIndex("_data")
        val path = if (columnIndex >= 0) {
            cursor.getString(columnIndex)
        } else {
            throw IllegalAccessException()
        }
        cursor.close()
        return path
    }

    private fun replaceFileName(fileName: String): String = "${fileName.replace(".", "_")}.jpeg"
}
