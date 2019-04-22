package com.login_signup_screendesign_demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword_Fragment extends Fragment implements
		OnClickListener {
	private static View view;

	private static EditText emailId;
	private static TextView submit, back;

	public ForgotPassword_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.forgotpassword_layout, container,
				false);
		initViews();
		setListeners();
		return view;
	}

	// เริ่มต้นมุมมอง
	private void initViews() {
		emailId = (EditText) view.findViewById(R.id.registered_emailid);
		submit = (TextView) view.findViewById(R.id.forgot_button);
		back = (TextView) view.findViewById(R.id.backToLoginBtn);

		//การตั้งค่าตัวเลือกข้อความผ่านการแสดงข้อความ
		XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			back.setTextColor(csl);
			submit.setTextColor(csl);

		} catch (Exception e) {
		}

	}

	// Set Listeners over buttons
	private void setListeners() {
		back.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backToLoginBtn:

			// Replace Login Fragment on Back Presses
			new MainActivity().replaceLoginFragment();
			break;

		case R.id.forgot_button:

			// Call Submit button task
			submitButtonTask();
			break;

		}

	}

//	ตรงนี้สำคัญ
	private void submitButtonTask() {
		String getEmailId = emailId.getText().toString();

		// รูปแบบสำหรับการตรวจสอบรหัสอีเมล
		Pattern p = Pattern.compile(Utils.regEx);

		// ตรงกับรูปแบบ
		Matcher m = p.matcher(getEmailId);

		// ก่อนอื่นตรวจสอบว่ารหัสอีเมลไม่ใช่โมฆะมิฉะนั้นจะแสดงว่ามีข้อผิดพลาด
		if (getEmailId.equals("") || getEmailId.length() == 0)

			new CustomToast().Show_Toast(getActivity(), view,
					"กรุณาใส่รหัสอีเมลของคุณ");

		// ตรวจสอบว่ารหัสอีเมลถูกต้องหรือไม่
		else if (!m.find())
			new CustomToast().Show_Toast(getActivity(), view,
					"รหัสอีเมลของคุณไม่ถูกต้อง");

		// อื่นส่งรหัสอีเมลและดึงรหัสผ่านหรือทำสิ่งที่คุณ
		else
			Toast.makeText(getActivity(), "ลืมรหัสผ่าน",
					Toast.LENGTH_SHORT).show();
	}
}