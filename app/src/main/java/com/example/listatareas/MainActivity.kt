package com.example.listatareas

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // val nameList= arrayListOf<String>()
        val nameList= arrayListOf<String>()
        val adapter  = ArrayAdapter(this,android.R.layout.simple_list_item_1,nameList)


        if (fileList().contains("notas.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("notas.txt"))
                val br = BufferedReader(archivo)
                var linea = br.readLine()
                val todo = StringBuilder()
                while (linea != null) {
                    todo.append(linea + "\n")
                    linea = br.readLine()
                }
                br.close()
                archivo.close()
                et1.setText(todo)

            } catch (e: IOException) {
            }
        }


        boton1.setOnClickListener(fun(it: View) {

            try {
                val out= PrintStream(openFileOutput("tareas.txt",Activity.MODE_APPEND))
                out.println(et1.text.toString())
                out.close()


            } catch (e: IOException) {
            }
            Toast.makeText(this, "Los datos fueron grabados", Toast.LENGTH_SHORT).show()

        })
        button3.setOnClickListener {
            val l=null
            val input= Scanner(openFileInput("tareas.txt"))
           // val nameList= arrayListOf<String>()


            while(input.hasNextLine()){
                val l=input.nextLine()
                nameList.add(l)


            }


           // val adapter  = ArrayAdapter(this,android.R.layout.simple_list_item_1,nameList)
            list_tareas.adapter=adapter

            input.close()



        }

        this.list_tareas.onItemLongClickListener = OnItemLongClickListener { av, v, pos, id ->

            Toast.makeText(this, "Los datos fueron grabados"+v.toString(), Toast.LENGTH_LONG).show()


            val selectedItem: String = nameList.get(pos)
           nameList.remove(selectedItem)
           adapter.notifyDataSetChanged()
            var file : File
            file= getFileStreamPath("tareas.txt")
            if (file.exists()){
                deleteFile("tareas.txt")



                adapter!!.notifyDataSetChanged()
            }
            val out= PrintStream(openFileOutput("tareas.txt",Activity.MODE_APPEND))
           // out.println(et1.text.toString())
            out.close()
            return@OnItemLongClickListener true
        }


    }

    fun readTareas(){

        val l=null
        val input= Scanner(openFileInput("tareas.txt"))
        val nameList= arrayListOf<String>()


        while(input.hasNextLine()){
            val l=input.nextLine()
            nameList.add(l)


        }


        val adapter  = ArrayAdapter(this,android.R.layout.simple_list_item_1,nameList)
        list_tareas.adapter=adapter

        input.close()


    }
    }



