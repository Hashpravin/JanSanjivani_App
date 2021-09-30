package com.example.jansanjivani;

import android.text.Editable;

class NewuserHelperclass {

      String name,user,pass,ph,add;

      public NewuserHelperclass(Editable name, Editable username, Editable pass,Editable ph,Editable add) {

      }

      public NewuserHelperclass(String name, String user, String pass,String ph,String add) {
          this.name = name;
          this.user = user;
          this.pass = pass;
          this.ph = ph ;
          this.add = add ;
      }

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public String getUser() {
          return user;
      }

      public void setUser(String user) {
          this.user = user;
      }

      public String getPass() {
          return pass;
      }

      public void setPass(String pass) {
          this.pass = pass;
      }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }



    
}
