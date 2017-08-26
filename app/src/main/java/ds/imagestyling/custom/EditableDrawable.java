package ds.imagestyling.custom;

import android.graphics.Paint;

public interface EditableDrawable {
    public static final int CURSOR_BLINK_TIME = 400;

    public void setOnSizeChangeListener(OnSizeChange paramOnSizeChange);

    public void beginEdit();

    public void endEdit();



    public void setBounds(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);



    public static interface OnSizeChange {
        public void onSizeChanged(EditableDrawable paramEditableDrawable, float paramFloat1,
                                  float paramFloat2, float paramFloat3, float paramFloat4);
    }
}
