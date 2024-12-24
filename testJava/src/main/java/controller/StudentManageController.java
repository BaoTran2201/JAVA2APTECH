package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

import view.StudentManagementView;

public class StudentManageController implements Action {
	StudentManagementView view;

	public StudentManageController(StudentManagementView view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

}
