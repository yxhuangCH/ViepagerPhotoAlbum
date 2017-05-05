package com.gdysj.otherdemo;

/**
 * Author: yxhuang
 * Date: 2017/4/15
 * Email: yxhuang@gmail.com
 */

public class InfoBean {

    private String errMsg;

    private String buy_user_info;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getBuy_user_info() {
        return buy_user_info;
    }

    public void setBuy_user_info(String buy_user_info) {
        this.buy_user_info = buy_user_info;
    }

    @Override
    public String toString() {
        return "InfoBean{" +
                "errMsg='" + errMsg + '\'' +
                '}';
    }

//        public UserInfo getBuy_user_info() {
//        return buy_user_info;
//    }
//
//    public void setBuy_user_info(UserInfo buy_user_info) {
//        this.buy_user_info = buy_user_info;
//    }


    public static class UserInfo{
        private String bank_no;
        private String ref_no;

        public String getBank_no() {
            return bank_no;
        }

        public void setBank_no(String bank_no) {
            this.bank_no = bank_no;
        }

        public String getRef_no() {
            return ref_no;
        }

        public void setRef_no(String ref_no) {
            this.ref_no = ref_no;
        }
    }
}
