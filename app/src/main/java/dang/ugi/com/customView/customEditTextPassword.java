package dang.ugi.com.customView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import dang.ugi.com.R;

/**
 * Created by DANG on 9/5/2016.
 */
public class customEditTextPassword extends EditText {
    Drawable eye,eyeStrike;
    boolean visible=false;
    private boolean useStrike;
    Drawable drawable;
    public customEditTextPassword(Context context) {
        super(context);
        constructor(null);
    }

    public customEditTextPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        constructor(attrs);
    }

    public customEditTextPassword(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public customEditTextPassword(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        constructor(attrs);
    }
    public void constructor(AttributeSet atts){
        if(atts != null){
            TypedArray array = getContext().getTheme().obtainStyledAttributes(atts,R.styleable.customEditTextPassword,0,0);
            this.useStrike = array.getBoolean(R.styleable.customEditTextPassword_useStrike,false);
        }
        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off).mutate();
        apply();
    }
    public void apply(){
        setInputType(InputType.TYPE_CLASS_TEXT |(visible?InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD:InputType.TYPE_TEXT_VARIATION_PASSWORD));
         Drawable[] drawables = getCompoundDrawables();
         drawable = useStrike && !visible?eyeStrike:eye;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()== MotionEvent.ACTION_DOWN && event.getX() >=(getRight()-drawable.getBounds().width())){
            visible = !visible;
            apply();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
