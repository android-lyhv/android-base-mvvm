import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Keyboard IME Util
 *
 * @author Binc
 */
object KeyboardUtil {
    @SuppressLint("ClickableViewAccessibility")
    fun onTouchToHideKeyboard(activity: Activity, view: View) {
        if (view !is EditText || !view.isFocusable()) {
            view.setOnTouchListener { _, _ ->
                hideKeyboard(activity.baseContext, view)
                view.clearFocus()
                false
            }
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                onTouchToHideKeyboard(activity, innerView)
            }
        }
    }

    private fun hideKeyboard(context: Context, v: View) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
    }
}
