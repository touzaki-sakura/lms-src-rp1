package jp.co.sss.lms.util;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jp.co.sss.lms.form.DailyAttendanceForm;

public class TimeValidator implements ConstraintValidator<Time, Object> {

	private String attendanceList;

	@Override
	public void initialize(Time annotation) {
		// TODO 自動生成されたメソッド・スタブ
		this.attendanceList = annotation.feildAttendanceList();
	}

	//	List<DailyAttendanceForm>
	//入力チェック(7/23)　
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);

		List<DailyAttendanceForm> attendanceList = (List<DailyAttendanceForm>) beanWrapper
				.getPropertyDescriptor(this.attendanceList);
		boolean errorFlg = true;
		LinkedHashMap<Integer, String> errorMessags = new LinkedHashMap<Integer, String>();
		Integer count = 0;
		for (DailyAttendanceForm form : attendanceList) {
			Integer trainingStartTimeHour = form.getTrainingStartTimeHour();
			Integer trainingStartTimeMinute = form.getTrainingStartTimeMinute();
			Integer trainingEndTimeHour = form.getTrainingEndTimeHour();
			Integer trainingEndTimeMinute = form.getTrainingEndTimeMinute();
			Integer blankTime = form.getBlankTime();

			//出勤時間に一方未入力がある場合
			if ((trainingStartTimeHour == null && trainingStartTimeMinute != null)
					|| (trainingStartTimeHour != null && trainingStartTimeMinute == null)) {
				errorFlg = false;
				errorMessags.put(count, "エラーメッセージ");
			}
			//退勤時間一方未入力がある場合
			if ((trainingEndTimeHour == null && trainingEndTimeMinute != null)
					|| (trainingEndTimeHour != null && trainingEndTimeMinute == null)) {
				errorFlg = false;
				errorMessags.put(count, "エラーメッセージ");
			}
			//出勤時間が未入力、退勤時間が入力されている場合
			if ((trainingStartTimeHour == null && trainingStartTimeMinute == null)
					&& (trainingStartTimeHour != null && trainingStartTimeMinute != null)) {
				errorFlg = false;
				errorMessags.put(count, "エラーメッセージ");
			}
			//中抜け時間が勤務時間より長い場合
			if ((trainingEndTimeHour * 60 + trainingEndTimeMinute)
					- (trainingStartTimeHour * 60 + trainingStartTimeMinute) > blankTime) {
				errorFlg = false;
				errorMessags.put(count, "エラーメッセージ");
			}

			count++;
		}

		return errorFlg;
	}

}
