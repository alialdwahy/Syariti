package com.example.myapplication;

import java.io.Serializable;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class CarsI implements Serializable {
    private String name;
    private String model;
    private String price;
    private byte[] image;
    private String type;
    private String Traveleddistanc;
    private String Carno;
    private String nameaccunt;
    private String accountno;
    private String Accident;
    private String Licens;
    private String geartyp ;
    private String typereqouse ;



    public CarsI(String namecar, String pricecar, byte[] image ,String typecar, String modelcar, String Traveleddistanc, String Carno, String Accident, String Licens, String geartyp, String typereqouse) {
        this.name = namecar;
        this.price = pricecar;
        this.image = image;
        this.model = modelcar;
        this.type=typecar;
        this.Traveleddistanc = Traveleddistanc;
        this.Carno = Carno;
        this.Accident = Accident;
        this.Licens = Licens;
        this.geartyp= geartyp;
        this.typereqouse = typereqouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String namecar) {
        this.name = namecar;
    }

    public String getModel() {

        return model;
    }

    public void setModel(String modelcar) {

        this.model = modelcar;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String pricecar)
    {
        this.price = pricecar;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {

        this.image = image;
            }
    public String getType() {
        return type;
    }

    public void setType(String typecar)
    {
        this.type = typecar;
    }

    public String getTraveleddistanc() {
        return Traveleddistanc;
    }

    public void setTraveleddistanc(String Traveleddistanc) {
        this.Traveleddistanc = Traveleddistanc;
    }

    public String getCarno() {
        return Carno;
    }

    public void setCarno(String Carno) {
        this.Carno = Carno;
    }


    public String getAccident() {
        return Accident;
    }

    public void setAccident(String Accident) {
        this.Accident = Accident;
    }
    public String getLicens() {
        return Licens;
    }

    public void setLicens(String Licens) {
        this.Licens = Licens;
    }

    public String getgeartype() {
        return geartyp;
    }

    public void setgeartyp(String geartyp) {
        this.geartyp = geartyp;
    }

    public String gettypereqouse() {
        return typereqouse;
    }

    public void settypereqouse(String typereqouse) {
        this.typereqouse = typereqouse;
    }
}

