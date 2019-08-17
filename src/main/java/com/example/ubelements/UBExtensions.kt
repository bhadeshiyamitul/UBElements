package com.example.ubelements

class HandleTabSelections(vararg tabviewlist: UBImageView?) {

    private var viewlist: Array<out UBImageView?>? = null

    enum class MODESelections {
        SINGLEMODE, MULTIMODE
    }

    var mode: MODESelections = MODESelections.SINGLEMODE

    init {
        this.viewlist = tabviewlist
    }

    fun selectAt(i: Int) {
        when (mode) {
            MODESelections.SINGLEMODE -> {
                for (x in 0 until viewlist!!.size) {
                    viewlist!![x]!!.isImageSelected = x == i
                }
            }
            MODESelections.MULTIMODE -> {
                viewlist!![i]!!.isImageSelected = !viewlist!![i]!!.isImageSelected
            }
        }
    }

    fun deselectAt(i: Int) {
        when (mode) {
            MODESelections.SINGLEMODE -> {

            }
            MODESelections.MULTIMODE -> {
                viewlist!![i]!!.isImageSelected = false
            }
        }
    }
}