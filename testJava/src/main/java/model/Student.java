package model;

import java.util.Date;
import java.util.Objects;

public class Student {
	private int id;
	private String name;
	private boolean gender;
	private Date dob;
	private float diemMon1, diemMon2, diemMon3;
	private Tinh queQuan;

	public Student(int id, String name, boolean gender, Date dob, float diemMon1, float diemMon2, float diemMon3,
			Tinh queQuan) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.diemMon1 = diemMon1;
		this.diemMon2 = diemMon2;
		this.diemMon3 = diemMon3;
		this.queQuan = queQuan;
	}

	public Tinh getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(Tinh queQuan) {
		this.queQuan = queQuan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public float getDiemMon1() {
		return diemMon1;
	}

	public void setDiemMon1(float diemMon1) {
		this.diemMon1 = diemMon1;
	}

	public float getDiemMon2() {
		return diemMon2;
	}

	public void setDiemMon2(float diemMon2) {
		this.diemMon2 = diemMon2;
	}

	public float getDiemMon3() {
		return diemMon3;
	}

	public void setDiemMon3(float diemMon3) {
		this.diemMon3 = diemMon3;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", diemMon1=" + diemMon1
				+ ", diemMon2=" + diemMon2 + ", diemMon3=" + diemMon3 + ", queQuan=" + queQuan + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(diemMon1, diemMon2, diemMon3, dob, gender, id, name, queQuan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		var other = (Student) obj;
		return Float.floatToIntBits(diemMon1) == Float.floatToIntBits(other.diemMon1)
				&& Float.floatToIntBits(diemMon2) == Float.floatToIntBits(other.diemMon2)
				&& Float.floatToIntBits(diemMon3) == Float.floatToIntBits(other.diemMon3)
				&& Objects.equals(dob, other.dob) && gender == other.gender && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(queQuan, other.queQuan);
	}

}
