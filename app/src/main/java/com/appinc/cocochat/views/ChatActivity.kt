package com.appinc.cocochat.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.appinc.cocochat.adapters.ChatAdapter
import com.appinc.cocochat.App
import com.appinc.cocochat.models.Chat
import com.appinc.cocochat.R
import com.github.nkzawa.emitter.Emitter
import com.google.gson.Gson


class ChatActivity : AppCompatActivity() {

    val TAG = ChatActivity::class.java.simpleName

    private lateinit var usuario: String
    private lateinit var sala: String
    private lateinit var toolbar: Toolbar
    private var isUser: Boolean = false

    val gson: Gson = Gson()

    //For setting the recyclerView.
    val chatList: ArrayList<Chat> = arrayListOf();
    lateinit var chatRoomAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (intent != null && intent.extras != null) {
            usuario = intent.extras!!.getString("nombre", "")
            sala = intent.extras!!.getString("sala", "")
            supportActionBar?.title = intent.extras!!.getString("sala", "")
            /*supportActionBar?.subtitle = "En linea"*/
        }

        val hashMap: HashMap<String, String> = HashMap()
        hashMap["nombre"] = this.usuario
        hashMap["sala"] = this.sala

        //Get the nickname and roomname from entrance activity.

        App.socket.emit("subscribe", Gson().toJson(hashMap))
        App.socket.on("newUserToChatRoom", onNewUser)
        //Let's connect to our Chat room! :D
        //Register all the listener and callbacks here.
        /*
         mSocket.on("", onNewUser) // To know if the new user entered the room.
         mSocket.on("updateChat", onUpdateChat) // To update if someone send a message to chatroom
         mSocket.on("userLeftChatRoom", onUserLeft) // To know if the user left the chatroom.*/
    }

    /*override fun onDestroy() {
        super.onDestroy()

        if (App.socket.connected()) App.socket.disconnect()
    }*/

    var onNewUser = Emitter.Listener {
        val name = it[0] as String //This pass the userName!
        if (name.trim() != this.usuario) {
            runOnUiThread {
                toolbar.subtitle = "En línea"
            }
            Log.d(TAG, "on New User triggered.")
            return@Listener
        }
        /*
        val chat = //Message(name, "", roomName, MessageType.USER_JOIN.index)
        //addItemToRecyclerView(chat)*/
    }
    // <----- Callback functions ------->

    /* var onConnect = Emitter.Listener {
         //After getting a Socket.EVENT_CONNECT which indicate socket has been connected to server,
         //send userName and roomName so that they can join the room.
         val data = User(userName, roomName)
         val jsonData = gson.toJson(data) // Gson changes data object to Json type.
         mSocket.emit("subscribe", jsonData)
     }*/

    /*

    var onUserLeft = Emitter.Listener {
        val leftUserName = it[0] as String
        val chat: Message = Message(leftUserName, "", "", MessageType.USER_LEAVE.index)
        addItemToRecyclerView(chat)
    }

    var onUpdateChat = Emitter.Listener {
        val chat: Chat = gson.fromJson(it[0].toString(), Chat::class.java)
        chat.viewType = MessageType.CHAT_PARTNER.index
        addItemToRecyclerView(chat)
    }*/
}