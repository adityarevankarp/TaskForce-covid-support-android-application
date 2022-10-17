package com.revankars.taskforce;

public class model {
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAlsoHelp() {
        return AlsoHelp;
    }

    public void setAlsoHelp(String alsoHelp) {
        AlsoHelp = alsoHelp;
    }


    public String getAttender() {
        return Attender;
    }

    public void setAttender(String attender) {
        Attender = attender;
    }

    public String getBUnum6digi() {
        return BUnum6digi;
    }

    public void setBUnum6digi(String BUnum6digi) {
        this.BUnum6digi = BUnum6digi;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getDependents() {
        return Dependents;
    }

    public void setDependents(String dependents) {
        Dependents = dependents;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getHelpOn() {
        return HelpOn;
    }

    public void setHelpOn(String helpOn) {
        HelpOn = helpOn;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPlaceOfStay() {
        return PlaceOfStay;
    }

    public void setPlaceOfStay(String placeOfStay) {
        PlaceOfStay = placeOfStay;
    }

    public String getSRFID13Nos() {
        return SRFID13Nos;
    }

    public void setSRFID13Nos(String SRFID13Nos) {
        this.SRFID13Nos = SRFID13Nos;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public model(String alsoHelp,String address, String age, String attender, String BUnum6digi, String bloodGroup, String dependents, String gender, String helpOn, String name, String phone, String placeOfStay, String SRFID13Nos, String surname, String zone) {
        Address = address;
        Age = age;
        AlsoHelp = alsoHelp;
        Attender = attender;
        this.BUnum6digi = BUnum6digi;
        BloodGroup = bloodGroup;
        Dependents = dependents;
        Gender = gender;
        HelpOn = helpOn;
        Name = name;
        Phone = phone;
        PlaceOfStay = placeOfStay;
        this.SRFID13Nos = SRFID13Nos;
        Surname = surname;
        Zone = zone;

    }

    String Address,Age,AlsoHelp,Attender,BUnum6digi,BloodGroup,Dependents,Gender,HelpOn,Name,Phone,PlaceOfStay,SRFID13Nos,Surname,Zone;







model(){

}


}


