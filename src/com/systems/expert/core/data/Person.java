package com.systems.expert.core.data;

import java.time.LocalDate;

/**
 * Created by Kage on 2/20/14.
 */
public interface Person {

    public String getName();
    public  void setName(String name);

    public String getAddress();
    public  void setAddress(String address);

    public LocalDate getBirthDate();
    public  void setBirthDate(LocalDate birthDate);
    public int getAge();

    public boolean isIdentityCardCopyPresent();
    public void setIdentityCardCopyPresent(boolean identityCardCopyPresent);

    public Nationality getNationality();
    public void setNationality(Nationality nationality);

    public int getMaintainMatures();
    public void setMaintainMatures(int maintainMatures);

    public int getMaintainUnderage();
    public void setMaintainUnderage(int maintainUnderage);

    public boolean isPossessingHouse();
    public void setPossessingHouse(boolean possessingHouse);

    public boolean isPossessingAuto();
    public void setPossessingAuto(boolean possessingAuto);

}
