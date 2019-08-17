package com.example.ubelements

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.ubimageview.view.*


class UBImageView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {


    private var mainurl: Any? = null
    private var placeholder: Drawable? = null
    private var errorholder: Drawable? = null
    var ubLoadAnything: Any
        get() {
            return mainurl!!
        }
        set(value) {
            mainurl = value
            refreshView()
        }
    var ubLoadPlaceholder: Drawable
        get() {
            return placeholder!!
        }
        set(value: Drawable) {
            placeholder = value
            refreshView()
        }

    var ubImageUrl: String
        get() {
            return mainurl!! as String
        }
        set(value: String) {
            mainurl = value
            refreshView()
        }
    var ubLoadErrorholder: Drawable
        get() {
            return errorholder!!
        }
        set(value: Drawable) {
            errorholder = value
            refreshView()
        }
    private var mImageview: ImageView? = null
    private var mRelativeRoot: RelativeLayout? = null
    var selection: Boolean = false
    var isImageSelected: Boolean
        get() {
            return selection

        }
        set(value) {
            selection = value
            refreshView()
        }
    private var imageSelectedDrawable: Drawable? = null
    private var imageUnSelectedDrawable: Drawable? = null
    private var imageSelectedViewHeight: Float? = null
    private var imageSelectedViewWidth: Float? = null
    private var imageUnSelectedViewHeight: Float? = null
    private var imageUnSelectedViewWidth: Float? = null
    private var imageViewHeight: Float? = null
    private var imageViewWidth: Float? = null

    init {

        inflate(context, R.layout.ubimageview, this)

        mImageview = findViewById<ImageView>(R.id.imageview)
        mRelativeRoot = findViewById<RelativeLayout>(R.id.relative_root)

        val attribute = context.obtainStyledAttributes(attrs, R.styleable.UBImageView)

        val imagedrawble = attribute.getDrawable(R.styleable.UBImageView_ubImageDrawable)
        imageViewHeight = attribute.getFloat(R.styleable.UBImageView_ubViewHeight, 0f)
        imageViewWidth = attribute.getFloat(R.styleable.UBImageView_ubViewWidth, 0f)

        imageSelectedViewHeight =
            attribute.getFloat(R.styleable.UBImageView_ubSelectedViewHeight, 0f)
        imageSelectedViewWidth = attribute.getFloat(R.styleable.UBImageView_ubSelectedViewWidth, 0f)
        imageUnSelectedViewHeight =
            attribute.getFloat(R.styleable.UBImageView_ubUnSelectedViewHeight, 0f)
        imageUnSelectedViewWidth =
            attribute.getFloat(R.styleable.UBImageView_ubUnSelectedViewWidth, 0f)


        val imageUrl = attribute.getString(R.styleable.UBImageView_ubImageUrl)
        val imageslected = attribute.getDrawable(R.styleable.UBImageView_ubImageSelectedDrawable)
        val imageunselected =
            attribute.getDrawable(R.styleable.UBImageView_ubImageUnselectedDrawable)
        selection = attribute.getBoolean(R.styleable.UBImageView_ubIsImageSelected, false)
        imageSelectedDrawable = imageslected
        imageUnSelectedDrawable = imageunselected

        val scaleType =
            Direction.values()[attribute.getInt(
                R.styleable.UBImageView_ubScaleType,
                0
            )]

        mImageview!!.scaleType = when (scaleType) {
            Direction.fitStart -> {
                ImageView.ScaleType.FIT_START
            }
            Direction.centerCrop -> {
                ImageView.ScaleType.CENTER_CROP
            }
            Direction.center -> {
                ImageView.ScaleType.CENTER
            }
            Direction.matrix -> {
                ImageView.ScaleType.MATRIX
            }
            Direction.centerInside -> {
                ImageView.ScaleType.CENTER_INSIDE
            }
            Direction.fitCenter -> {
                ImageView.ScaleType.FIT_CENTER
            }
            Direction.fitEnd -> {
                ImageView.ScaleType.FIT_END
            }
            else -> {
                ImageView.ScaleType.FIT_XY
            }
        }
        placeholder = attribute.getDrawable(R.styleable.UBImageView_ubImagePlaceHolder)
        errorholder = attribute.getDrawable(R.styleable.UBImageView_ubImageErrorHolder)




        mainurl = when {
            imagedrawble != null -> imagedrawble
            imageUrl != null -> imageUrl
            else -> null
        }


        refreshView()

        attribute.recycle()

    }

    private fun refreshView() {
        if (imageSelectedDrawable != null &&
            imageUnSelectedDrawable != null
        ) {

            if (selection) {
                if (imageSelectedViewWidth != 0f &&
                    imageSelectedViewHeight != 0f
                ) {
                    ubSetWidthHeight(imageSelectedViewWidth!!, imageSelectedViewHeight!!)
                } else {
                    setNormalHeightWidth()
                }
                mImageview!!.setImageDrawable(imageSelectedDrawable)

            } else {
                if (imageUnSelectedViewWidth != 0f &&
                    imageUnSelectedViewHeight != 0f
                ) {

                    ubSetWidthHeight(imageUnSelectedViewWidth!!, imageUnSelectedViewHeight!!)
                } else {
                    setNormalHeightWidth()
                }

                mImageview!!.setImageDrawable(imageUnSelectedDrawable)

            }

        } else {
            setNormalHeightWidth()
            if (errorholder != null && placeholder != null) {
                Glide
                    .with(this)
                    .load(mainurl)
                    .placeholder(placeholder)
                    .error(errorholder!!)
                    .into(imageview!!)
            } else if (errorholder != null) {
                Glide
                    .with(this)
                    .load(mainurl)
                    .error(errorholder!!)
                    .into(imageview!!)
            } else if (placeholder != null) {
                Glide
                    .with(this)
                    .load(mainurl)
                    .placeholder(placeholder)
                    .into(imageview!!)
            } else {
                Glide
                    .with(this)
                    .load(mainurl)
                    .into(imageview!!)
            }
        }


    }

    private fun setNormalHeightWidth() {
        if (imageViewHeight != 0f &&
            imageViewWidth != 0f
        ) {
            ubSetWidthHeight(
                viewWidth = imageViewWidth!!,
                viewHeight = imageViewHeight!!
            )
        }
    }

    var ubImageDrawable: Drawable
        get() {
            return mainurl as Drawable
        }
        set(value) {
            mainurl = value
            refreshView()
        }


    var ubImageSelectedDrawable: Drawable
        get() {
            return imageSelectedDrawable!!
        }
        set(value) {
            imageSelectedDrawable = value
            refreshView()
        }

    var ubImageUnselectedDrawable: Drawable
        get() {
            return imageUnSelectedDrawable!!
        }
        set(value) {
            imageUnSelectedDrawable = value
            refreshView()
        }


    fun getubImageview(): ImageView? {
        return mImageview
    }


    fun ubSelectedViewWH(
        viewWidth: Float, viewHeight: Float,
        ubImageSelectedDrawable: Drawable? = null
    ) {
        if (ubImageSelectedDrawable != null) {
            imageSelectedDrawable = ubImageSelectedDrawable
        }
        imageSelectedViewHeight = viewHeight
        imageSelectedViewWidth = viewWidth
        //ubSetWidthHeight(imageSelectedViewWidth!!, imageSelectedViewHeight!!)
        refreshView()
    }

    fun ubUnSelectedViewWH(
        viewWidth: Float,
        viewHeight: Float,
        ubImageUnselectedDrawable: Drawable? = null
    ) {
        if (ubImageUnselectedDrawable != null) {
            imageUnSelectedDrawable = ubImageUnselectedDrawable
        }
        imageUnSelectedViewHeight = viewHeight
        imageUnSelectedViewWidth = viewWidth
        //ubSetWidthHeight(imageUnSelectedViewWidth!!, imageUnSelectedViewHeight!!)
        refreshView()
    }

    fun ubViewWH(viewWidth: Float, viewHeight: Float) {
        imageViewHeight = viewHeight
        imageViewWidth = viewWidth
        // ubSetWidthHeight(imageViewWidth!!, imageViewHeight!!)
        refreshView()
    }

    private fun ubSetWidthHeight(viewWidth: Float, viewHeight: Float) {

        val layoutParams = RelativeLayout.LayoutParams(dpToPx(viewWidth), dpToPx(viewHeight))
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        imageview.layoutParams = layoutParams
    }

    private fun dpToPx(dp: Float): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    enum class Direction {
        fitXY,
        fitStart,
        centerCrop,
        center,
        matrix,
        centerInside,
        fitCenter,
        fitEnd
    }
}