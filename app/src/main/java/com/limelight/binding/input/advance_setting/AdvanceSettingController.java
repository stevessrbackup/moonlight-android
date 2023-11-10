package com.limelight.binding.input.advance_setting;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.limelight.R;
import com.limelight.binding.input.virtual_controller.VirtualController;
import com.limelight.binding.input.virtual_controller.VirtualControllerConfigurationLoader;
import com.limelight.binding.input.virtual_controller.VirtualControllerElement;

import java.nio.Buffer;

public class AdvanceSettingController {

    private FrameLayout fatherLayout;
    private FrameLayout advanceSettingLayout;
    private Button setting1Button;
    private Button setting2Button;
    private Button setting3Button;
    //辅助切换设置按钮
    private Button currentSettingButton;
    private LinearLayout setting1Layout;
    private LinearLayout setting2Layout;
    private LinearLayout setting3Layout;
    //辅助切换设置界面
    private LinearLayout currentSettingLayout;
    private RadioGroup advanceSettingRadioGroup;
    private Context context;
    private Button buttonConfigure;

    public AdvanceSettingController(FrameLayout layout, final Context context) {
        this.fatherLayout = layout;
        this.context = context;

        advanceSettingLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.advance_setting_view,null);
        advanceSettingRadioGroup = advanceSettingLayout.findViewById(R.id.advance_setting_radio_group);
        setting1Button = advanceSettingLayout.findViewById(R.id.advance_setting_1_button);
        setting2Button = advanceSettingLayout.findViewById(R.id.advance_setting_2_button);
        setting3Button = advanceSettingLayout.findViewById(R.id.advance_setting_3_button);
        setting1Layout = advanceSettingLayout.findViewById(R.id.advance_setting_1_layout);
        setting2Layout = advanceSettingLayout.findViewById(R.id.advance_setting_2_layout);
        setting3Layout = advanceSettingLayout.findViewById(R.id.advance_setting_3_layout);
        /*
        * 设置界面初始化，最开始是"设置1"被选中,初始化的内容有
        * "设置1"的按钮背景需要设置为黑色，这个在layout中实现
        * "设置1"的界面需要显示，这个在layout中实现
        * 把当前的设置项设置为"设置1"，这个在下面实现
        * */
        currentSettingButton = setting1Button;
        currentSettingLayout = setting1Layout;
        advanceSettingRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.advance_setting_1_button){
                    switchAdvanceSetting(setting1Button,setting1Layout);
                }else if (checkedId == R.id.advance_setting_2_button){
                    switchAdvanceSetting(setting2Button,setting2Layout);
                }else if (checkedId == R.id.advance_setting_3_button){
                    switchAdvanceSetting(setting3Button,setting3Layout);
                }
            }
        });


        buttonConfigure = new Button(context);
        buttonConfigure.setAlpha(0.25f);
        buttonConfigure.setFocusable(false);
        buttonConfigure.setBackgroundResource(R.drawable.ic_settings);
        buttonConfigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"wangguan",Toast.LENGTH_SHORT);
                advanceSettingLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void switchAdvanceSetting(Button settingButton,LinearLayout settingLayout){
        if (currentSettingButton != settingButton){
            currentSettingButton.setBackgroundColor(context.getResources().getColor(R.color.advance_setting_button_background));
            currentSettingLayout.setVisibility(View.GONE);
            currentSettingButton = settingButton;
            currentSettingLayout = settingLayout;
            currentSettingButton.setBackgroundColor(context.getResources().getColor(R.color.advance_setting_background));
            currentSettingLayout.setVisibility(View.VISIBLE);
        }
    }

    public void refreshLayout(){
        DisplayMetrics screen = context.getResources().getDisplayMetrics();

        int buttonSize = (int)(screen.heightPixels*0.06f);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(buttonSize, buttonSize);
        params.leftMargin = 15;
        params.topMargin = 15;
        fatherLayout.addView(buttonConfigure, params);
    }
}