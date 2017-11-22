package com.bwie.usercenter.presenter;
import android.text.TextUtils;
import com.bwie.usercenter.bean.PersonBean;
import com.bwie.usercenter.model.ModelCallBack;
import com.bwie.usercenter.model.getDataModel;
import com.bwie.usercenter.view.InterfaceView;
/**
 * MVP的三层编写步骤：
 * 3. 再写presenter层普通类，多态获取其它两个接口的实现类，并进行相应数据的逻辑操作
 */
public class getDataPresenter {
    private InterfaceView.FirstInterface firstInterface;
    private InterfaceView.SecondInterface secondInterface;
    private ModelCallBack.PersonInterface personInterface;
    private getDataModel getDataModel;

    //声明登录页面view
    public getDataPresenter(InterfaceView.FirstInterface firstInterface) {
        this.firstInterface = firstInterface;
        getDataModel = new getDataModel();
    }
    //声明注册页面view
    public getDataPresenter(InterfaceView.SecondInterface secondInterface) {
        this.secondInterface = secondInterface;
        getDataModel = new getDataModel();
    }
    //声明登录页面view
    public getDataPresenter(ModelCallBack.PersonInterface personInterface) {
        this.personInterface = personInterface;
        getDataModel = new getDataModel();
    }

    //login登录方法判断输入内容是否为空
    public void login(final String tel,String pwd){
        if (TextUtils.isEmpty(tel)  || !isMobile(tel.toString())){
            firstInterface.telEmpty();
            return;
        }
        if ( TextUtils.isEmpty(pwd) || pwd.length() <6){
            firstInterface.pwdEmpty();
            return;
        }

        //调用Model层 代表登录的类，进行数据的存取，把从Model层类是否获取到数据的结果传到View 层 接口类进行调用显示
        getDataModel.LoginData(tel, pwd, new ModelCallBack.LoginInterface() {
            @Override
            public void success(Object object) {
                firstInterface.success(object);
            }
            @Override
            public void failed(int code) {
                firstInterface.failed(code);
            }
        });
    }

    //reg注册方法判断输入内容是否为空
    public void reg(String tel,String pwd){
        if (TextUtils.isEmpty(tel) || !isMobile(tel.toString())){
            secondInterface.telEmpty();
            return;
        }
        if ( TextUtils.isEmpty(pwd) || pwd.length() <6){
            secondInterface.pwdEmpty();
            return;
        }

        //调用Model层 代表登录的类，进行数据的存取，把从Model层类是否获取到数据的结果传到View 层 接口类进行调用显示
        getDataModel.LoginData(tel, pwd, new ModelCallBack.LoginInterface() {
            @Override
            public void success(Object object) {
                secondInterface.sucess(object);
            }
            @Override
            public void failed(int code) {
                secondInterface.failed(code);
            }
        });
    }

    //个人中心 获取id
    public void person(String uid){
        getDataModel.PersonData(uid, new ModelCallBack.PersonInterface() {
            @Override
            public void success(PersonBean personBean) {
                personInterface.success(personBean);
            }

            @Override
            public void failed(int code) {
                personInterface.failed(code);
            }
        });
    }

    //判断是否为手机号,本地使用正则表达式校验手机号为合法手机号
    /**
     * 验证手机格式,本地校验手机号，通过正则表达式校验
     */
    private static boolean  isMobile(String number) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        */
        String num = "[1][358]\\d{9}";//表示手机号一共11位，"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

}
