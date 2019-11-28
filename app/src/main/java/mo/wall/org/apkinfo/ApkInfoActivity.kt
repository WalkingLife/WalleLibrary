package mo.wall.org.apkinfo

import android.os.Bundle
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import mo.wall.org.R
import mo.wall.org.base.BaseAppCompatActivity

/**
 * Copyright (C), 2018-2019
 * Author: ziqimo
 * Date: 2019-11-27 12:59
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
class ApkInfoActivity : BaseAppCompatActivity() {

    private lateinit var mEdPkg: EditText
    private lateinit var mEdAfp: EditText
    private lateinit var mEdAsha: EditText
    private lateinit var mEdSize: EditText
    private lateinit var mBtn: Button


    override fun handleMessageAct(msg: Message?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apkinfo)

        mEdPkg = findViewById<EditText>(R.id.ed_pkg)
        mEdAfp = findViewById<EditText>(R.id.ed_afp)
        mEdAsha = findViewById<EditText>(R.id.ed_asha)
        mEdSize = findViewById<EditText>(R.id.ed_size)
        mBtn = findViewById<Button>(R.id.btn)



        mBtn.setOnClickListener {
            val packageName = mEdPkg.text.toString().trim()
            if (TextUtils.isEmpty(packageName)) {
                return@setOnClickListener
            }
            val g = AFPUtils.G(this@ApkInfoActivity, packageName)
            Log.i("g", g)
            mEdAfp.setText(g)
            val g1 = ASHASUtils.G(this@ApkInfoActivity, packageName)
            mEdAsha.setText(g1)
            Log.i("g1", g1)

            val d = apk_sizeUtils.D(this@ApkInfoActivity, packageName)
            mEdSize.setText("" + d)
            Log.i("d", d.toString())
        }
    }

}