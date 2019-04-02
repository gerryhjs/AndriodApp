package  com.example.root.gerrytester;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
        private Button mCPUButton;
        private boolean bTestThread=true;private boolean bFlag=true;

        protected void onCreate(Bundle savedInstanceState)
        {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                mCPUButton= (Button)findViewById(R.id);
                mCPUButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view)
                        {
                                if(bFlag)
                                {
                                        bFlag=false;
                                        mCPUButton.setText("stop");
                                        for(int i =0; i<100; i++)
                                                cpuTest();
                                }
                                else{
                                        bFlag=true;
                                        mCPUButton.setText("cpu");
                                        bTestThread=false;}
                        }});
        }


        private void cpuTest()
        {
                new Thread(new Runnable()
                {
                        public void run()
                        {
                                while(bTestThread){}}}).start();
        }
}

