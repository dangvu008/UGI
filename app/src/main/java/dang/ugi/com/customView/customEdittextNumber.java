package dang.ugi.com.customView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import dang.ugi.com.R;

/**
 * Created by DANG on 9/6/2016.
 */
@SuppressLint("AppCompatCustomView")
public class customEdittextNumber extends EditText {
    Drawable clear,noneClear,drawable;
    Boolean isClear = false;
    public customEdittextNumber(Context context) {
        super(context);
        constructor();
    }

    public customEdittextNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        constructor();
    }

    public customEdittextNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public customEdittextNumber(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        constructor();
    }
    private void constructor(){
        clear = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear).mutate();
        noneClear = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();
        apply();
    }
    private void apply(){
        setInputType(InputType.TYPE_CLASS_NUMBER);
        Drawable[] drawables = getCompoundDrawables();
         drawable = isClear?clear:noneClear;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter==0 && start==0){
            isClear = false;
            apply();
        }else{
            isClear =true;
            apply();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN&&event.getX()>=(getRight()-drawable.getBounds().width())){
            setText("");
        }
        return super.onTouchEvent(event);
    }
}
