package com.newproject.bob.testmyserver;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.newproject.bob.testmyserver.constant.AppConfig;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private TextView mTvShow;
    private Context mContext;
    private String urlStr = "http://172.21.8.223:8080/home/jsontest";//ip为当前的网络ip，加上端口号和虚拟路径
    private List<Student> studentList = null;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            studentList = (List<Student>)msg.obj;
            if(null != studentList){
                mLv.setVisibility(View.VISIBLE);
                MyAdapter adapter = new MyAdapter(mContext,studentList);
                mLv.setAdapter(adapter);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mLv = findViewById(R.id.lv_show_data);
        mTvShow = (TextView)findViewById(R.id.tv_show);

        //把Student类封装的list里加6组数据
        List<Student> studentList1 = new ArrayList<>();
        for(int i = 0;i<6;i++){
            Student student = new Student();
            student.setName("Alex"+i);
            student.setAge(String.valueOf(Integer.valueOf("23")+i));
            studentList1.add(student);
        }
        addMoreStudents(studentList1);
    }

    /**
     * 批量添加student到mysql数据库
     * */
    private void addMoreStudents(List<Student> studentList){
        //把list转换成json格式
        String studentsStr = JSON.toJSONString(studentList);
        //下面都是request相关的操作，初始化之类的
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        //加入json
        builder.add("students",studentsStr);
        final Request request = new Request.Builder()
                .url(urlStr)//访问的虚拟地址
                .post(builder.build())
                .build();
        System.out.println("------------------"+request);
        //开始request
        Call call = mOkHttpClient.newCall(request);
        //回调接口
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            //如果request失败，操作
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String responseStr = response.body().string();
                System.out.println("99999999"+responseStr);//获取response的body，应该也要返回json，但是不知道怎么弄。。
                //下面就是解析json
//                List<Student> studentEntities = new ArrayList<>();
//                studentEntities = com.alibaba.fastjson.JSONArray.parseArray(responseStr,Student.class);
//                Message msg = mHandler.obtainMessage();
//                msg.obj = studentEntities;
//                mHandler.sendMessage(msg);
            }
        });
    }

    /**
     * 插入单个student到mysql
     * **/

    //下面注释掉的大同小异，就是传给spring boot的虚拟地址跟json不一样而已


//    private void insertInto(String name,String age){
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        FormEncodingBuilder builder = new FormEncodingBuilder();
//        builder.add("name",name);
//        builder.add("age",age);
//        final Request request = new Request.Builder()
//                .url(AppConfig.INSERT_STUDENT)
//                .post(builder.build())
//                .build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                String responseStr = response.body().string();
//                List<Student> studentEntities = new ArrayList<>();
//                studentEntities = com.alibaba.fastjson.JSONArray.parseArray(responseStr,Student.class);
//                Message msg = mHandler.obtainMessage();
//                msg.obj = studentEntities;
//                mHandler.sendMessage(msg);
//            }
//        });
//    }
//
//    /**
//     * 根据name删除student
//     * **/
//    private void delteStudentByName(String name) throws IOException {
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        FormEncodingBuilder builder = new FormEncodingBuilder();
//        builder.add("name",name);
//        final Request request = new Request.Builder()
//                            .url(AppConfig.DELETE_STUDENT_BY_NAME)
//                            .post(builder.build())
//                .build();
//      /*  Response response = mOkHttpClient.newCall(request).execute();*/
//      Call call = mOkHttpClient.newCall(request);
//      call.enqueue(new Callback() {
//          @Override
//          public void onFailure(Request request, IOException e) {
//
//          }
//
//          @Override
//          public void onResponse(Response response) throws IOException {
//              String responseStr = response.body().string();
//              List<Student> studentEntities = new ArrayList<>();
//              studentEntities = com.alibaba.fastjson.JSONArray.parseArray(responseStr,Student.class);
//              Message msg = mHandler.obtainMessage();
//              msg.obj = studentEntities;
//              mHandler.sendMessage(msg);
//          }
//      });
//    }

    /**
     * 得到数据库所有的数据
     * **/
    private void getData(){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(urlStr).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String responseStr = response.body().string();
                Message msg = mHandler.obtainMessage();
                msg.obj = responseStr;
                mHandler.sendMessage(msg);
            }
        });
    }

    //传一个键值对，应该是作者测试用的
    private void getJsonData(){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("name","Bob");
        final Request request = new Request.Builder().url(urlStr).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String responseStr = response.body().string();
                List<Student> studentEntities = new ArrayList<>();
                studentEntities = com.alibaba.fastjson.JSONArray.parseArray(responseStr,Student.class);
                Message msg = mHandler.obtainMessage();
                msg.obj = studentEntities;
                mHandler.sendMessage(msg);
            }
        });
    }



//下面是listview显示，可以忽略
    private class MyAdapter extends BaseAdapter{

        private List<Student> studentEntities = new ArrayList<>();
        private Context context;

        public MyAdapter(Context context,List<Student> girlEntities){
            this.context = context;
            this.studentEntities = girlEntities;
        }


        @Override
        public int getCount() {
            if(studentEntities.size() != 0){
                return studentEntities.size();
            }else{
                return 0;
            }
        }

        @Override
        public Object getItem(int i) {
            if(studentEntities.size() != 0){
                return studentEntities.get(i);
            }else{
                return null;
            }
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder mViewHolder = null;
            if(null == view){
                mViewHolder = new ViewHolder();
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_adapter,null);
                mViewHolder.mTvName = (TextView) view.findViewById(R.id.name);
                mViewHolder.mTvAge = (TextView) view.findViewById(R.id.age);
                view.setTag(mViewHolder);
            }else{
                mViewHolder = (ViewHolder) view.getTag();
            }

            Student girlEntity = studentEntities.get(i);
            mViewHolder.mTvName.setText(girlEntity.getName());
            mViewHolder.mTvAge.setText(girlEntity.getAge()+"");
            return view;
        }

    }

    private class ViewHolder{
        private TextView mTvName,mTvAge;
    }

}
