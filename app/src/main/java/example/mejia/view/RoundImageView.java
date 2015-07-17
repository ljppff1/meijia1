package example.mejia.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import main.ljppff.com.meijia1.R;


public class RoundImageView extends View
{

    /**
     * TYPE_CIRCLE / TYPE_ROUND
     */
    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;

    /**
     * Õº∆¨
     */
    private Bitmap mSrc;

    /**
     * ‘≤Ω«µƒ¥Û–°
     */
    private int mRadius;

    /**
     * øÿº˛µƒøÌ∂»
     */
    private int mWidth;
    /**
     * øÿº˛µƒ∏ﬂ∂»
     */
    private int mHeight;

    public RoundImageView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context)
    {
        this(context, null);
    }

    /**
     * ≥ı ºªØ“ª–©◊‘∂®“Âµƒ≤Œ ˝
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public RoundImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.RoundImageView, defStyle, 0);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.RoundImageView_src:
                    mSrc = BitmapFactory.decodeResource(getResources(),
                            a.getResourceId(attr, 0));
                    break;
                case R.styleable.RoundImageView_type1:
                    type = a.getInt(attr, 0);// ƒ¨»œŒ™Circle
                    break;
                case R.styleable.RoundImageView_borderRadius:
                    mRadius = a.getDimensionPixelSize(attr, (int) TypedValue
                            .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                                    getResources().getDisplayMetrics()));// ƒ¨»œŒ™10DP
                    break;
            }
        }
        a.recycle();
    }

    /**
     * º∆À„øÿº˛µƒ∏ﬂ∂»∫ÕøÌ∂»
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * …Ë÷√øÌ∂»
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mWidth = specSize;
        } else
        {
            // ”…Õº∆¨æˆ∂®µƒøÌ
            int desireByImg = getPaddingLeft() + getPaddingRight()
                    + mSrc.getWidth();
            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mWidth = Math.min(desireByImg, specSize);
            } else

                mWidth = desireByImg;
        }

        /***
         * …Ë÷√∏ﬂ∂»
         */

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mHeight = specSize;
        } else
        {
            int desire = getPaddingTop() + getPaddingBottom()
                    + mSrc.getHeight();

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mHeight = Math.min(desire, specSize);
            } else
                mHeight = desire;
        }

        setMeasuredDimension(mWidth, mHeight);

    }

    /**
     * ªÊ÷∆
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        switch (type)
        {
            // »Áπ˚ «TYPE_CIRCLEªÊ÷∆‘≤–Œ
            case TYPE_CIRCLE:
                int min = Math.min(mWidth, mHeight);
                /**
                 * ≥§∂»»Áπ˚≤ª“ª÷¬£¨∞¥–°µƒ÷µΩ¯––—πÀı
                 */
                mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);
                canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);
                break;
            case TYPE_ROUND:
                canvas.drawBitmap(createRoundConerImage(mSrc), 0, 0, null);
                break;

        }

    }

    /**
     * ∏˘æ›‘≠Õº∫Õ±‰≥§ªÊ÷∆‘≤–ŒÕº∆¨
     *
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int min)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Config.ARGB_8888);
        /**
         * ≤˙…˙“ª∏ˆÕ¨—˘¥Û–°µƒª≠≤º
         */
        Canvas canvas = new Canvas(target);
        /**
         *  ◊œ»ªÊ÷∆‘≤–Œ
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         *  π”√SRC_IN£¨≤Œøº…œ√ÊµƒÀµ√˜
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * ªÊ÷∆Õº∆¨
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * ∏˘æ›‘≠ÕºÃÌº”‘≤Ω«
     *
     * @param source
     * @return
     */
    private Bitmap createRoundConerImage(Bitmap source)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rect, mRadius, mRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }
}
