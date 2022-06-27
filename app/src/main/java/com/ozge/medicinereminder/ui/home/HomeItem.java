package com.ozge.medicinereminder.ui.home;

public class HomeItem {

    private String medicineName;
    private String dosageSummary;

    public HomeItem(String medicineName, String dosageSummary) {
        this.medicineName = medicineName;
        this.dosageSummary = dosageSummary;
    }

    //cardviedeki ögeler.

    String getMedicineName() {
        return medicineName;
    }

    String getDosageSummary() {
        return dosageSummary;
    }
}
