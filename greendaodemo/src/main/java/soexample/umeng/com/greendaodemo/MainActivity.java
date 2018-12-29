package soexample.umeng.com.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.greendaodemo.bean.Student;
import soexample.umeng.com.greendaodemo.mydao.StudentDao;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.Insert_Btn)
    Button InsertBtn;
    @BindView(R.id.Delete_Btn)
    Button DeleteBtn;
    @BindView(R.id.Update_Btn)
    Button UpdateBtn;
    @BindView(R.id.Select_Btn)
    Button SelectBtn;
    @BindView(R.id.Get_Content)
    TextView GetContent;
    private StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        studentDao = MyApp.getDaoSession().getStudentDao();

    }

    @OnClick({R.id.Insert_Btn, R.id.Delete_Btn, R.id.Update_Btn, R.id.Select_Btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Insert_Btn:
                insertStu();
                break;
            case R.id.Delete_Btn:
                deleteStu();
                break;
            case R.id.Update_Btn:
                updateStu();
                break;
            case R.id.Select_Btn:
                selectStu();
                break;
        }
    }
    //查询数据
    private void selectStu() {
        //查询所有数据
        GetContent.setText("");
        List<Student> students = studentDao.loadAll();
        GetContent.setText(students.toString());
    }

    //修改数据
    private void updateStu() {
        //修改必须要修改数据库里面存在的 数据、
        Student student = studentDao.load(1l);
        student.setAge(9);
        student.setName("嘻嘻嘻");
        student.setSex("哼");
        studentDao.update(student);
    }

    //删除数据  删除必须要删除数据库里面存在的数据
    private void deleteStu() {
        studentDao.deleteByKey(1l);
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
    }

    //插入数据
    private void insertStu() {
        Student student = new Student("啦啦啦", "嘿嘿", 3);
        long insert = studentDao.insert(student);
        if (insert>0){
            Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
        }
    }

}

