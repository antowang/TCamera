package com.abcew.camera.filter;

import com.abcew.camera.R;

/**
 * Created by laputan on 16/12/21.
 */

public class ColorFilterHighContrast extends LutColorFilter {

    public ColorFilterHighContrast() {
        super(R.string.color_filter_name_highcontrast, R.drawable.filter_preview_photo, R.drawable.lut_highcontrast);
    }
}
