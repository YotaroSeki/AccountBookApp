package jp.ac.titech.itpro.sdl.accountbook;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    RecyclerView recyclerView = findViewById(R.id.View);
    this.initDataSet();
    String itemString;
    ItemViewAdapter adapter = new ItemViewAdapter(new ArrayList<Item>());

    LinearLayoutManager manager = new LinearLayoutManager(this);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(manager);
    recyclerView.setAdapter(adapter);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private String initDataSet() {
    return new HttpGetTask().execute(n)
  }

  public final class HttpGetTask extends AsyncTask<URL, Void, String> {
    @Override
    protected String doInBackground(URL... urls) {
      // 取得したテキストを格納する変数
      final StringBuilder result = new StringBuilder();
      // アクセス先URL
      final URL url = urls[0];

      HttpURLConnection con = null;
      try {
        // ローカル処理
        // コネクション取得
        con = (HttpURLConnection) url.openConnection();
        con.connect();

        // HTTPレスポンスコード
        final int status = con.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
          // 通信に成功した
          // テキストを取得する
          final InputStream in = con.getInputStream();
          final String encoding = con.getContentEncoding();
          final InputStreamReader inReader = new InputStreamReader(in, encoding);
          final BufferedReader bufReader = new BufferedReader(inReader);
          String line = null;
          // 1行ずつテキストを読み込む
          while((line = bufReader.readLine()) != null) {
            result.append(line);
          }
          bufReader.close();
          inReader.close();
          in.close();
        }
      } catch (IOException e1) {
        e1.printStackTrace();
      } finally {
        if (con != null) {
          // コネクションを切断
          con.disconnect();
        }
      }
      return result.toString();
    }
  }

}
