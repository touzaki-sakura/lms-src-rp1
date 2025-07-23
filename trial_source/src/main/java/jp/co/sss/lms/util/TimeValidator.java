package jp.co.sss.lms.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeValidator implements ConstraintValidator<Time, Object> {

	private String trainingStartTimeHour;
	private String trainingStartTimeMinute;
	private String trainingEndTimeHour;
	private String trainingEndTimeMinute;

	@Override
	public void initialize(Time annotation) {
		// TODO 自動生成されたメソッド・スタブ
		this.trainingStartTimeHour = annotation.fieldTrainingStartTimeHour();
		this.trainingStartTimeMinute = annotation.fieldTrainingStartTimeMinute();
		this.trainingEndTimeHour = annotation.fieldTrainingEndTimeHour();
		this.trainingEndTimeMinute = annotation.fieldTrainingEndTimeMinute();
	}

	//入力チェック(7/23)
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);

		Integer trainingStartTimeHour = (Integer) beanWrapper.getPropertyValue(this.trainingStartTimeHour);
		Integer trainingStartTimeMinute = (Integer) beanWrapper.getPropertyValue(this.trainingStartTimeMinute);
		Integer trainingEndTimeHour = (Integer) beanWrapper.getPropertyValue(this.trainingEndTimeHour);
		Integer trainingEndTimeMinute = (Integer) beanWrapper.getPropertyValue(this.trainingEndTimeMinute);

		TrainingTime startTime = new TrainingTime(trainingStartTimeHour, trainingStartTimeMinute);
		TrainingTime endTime = new TrainingTime(trainingEndTimeHour, trainingEndTimeMinute);

		if ((trainingStartTimeHour == null && trainingStartTimeMinute != null)
				|| (trainingStartTimeHour != null && trainingStartTimeMinute == null)) {

		}
		if (startTime.isNotBlank() && endTime.isBlank()) {

		}

		return false;
	}

}
