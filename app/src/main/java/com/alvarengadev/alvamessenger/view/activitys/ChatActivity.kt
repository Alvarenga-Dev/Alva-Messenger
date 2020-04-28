package com.alvarengadev.alvamessenger.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.Chat
import com.alvarengadev.alvamessenger.presenter.chats.SaveChat
import com.alvarengadev.alvamessenger.presenter.messages.GetMessages
import com.alvarengadev.alvamessenger.presenter.messages.SaveMessage
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.alvarengadev.alvamessenger.utils.Consts
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    private val getMessages = GetMessages(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val bundle = intent.extras

        val emailFriend = bundle?.getString(Consts.FRIEND_EMAIL, "")
        val nameFriend = bundle?.getString(Consts.FRIEND_NAME, "")
        initToolbar(nameFriend)
        initListMessages(emailFriend)

        fabSendMessage.setOnClickListener { sendMessage(nameFriend, emailFriend, getInput()) }
    }

    private fun getInput() = etMessage.text.toString()

    private fun initListMessages(friendEmail: String?) {
        rcyChat.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = getMessages.get(friendEmail)
        }
    }

    private fun sendMessage(nameFriend: String?, friendEmail: String?, message: String) {
        val preferences = PreferencesUtils(this)
        val idUser = preferences.getUserKey()
        val nameUser = preferences.getUserName()

        val idFriend = Base64Actions.encodeBase64(friendEmail!!)
        val saveMessage = SaveMessage(applicationContext)

        if (message.trim().isNotEmpty()) {

            val isSaveMessageUser = saveMessage.save(idUser!!, idFriend, message)
            val isSaveMessageFriend = saveMessage.save(idFriend, idUser, message)

            if (!isSaveMessageFriend || !isSaveMessageUser) {
                Toast.makeText(applicationContext, "A", Toast.LENGTH_SHORT).show()
            } else {
                val chatUser = Chat(idFriend, nameFriend!!, message)
                val chatFriend = Chat(idUser, nameUser!!, message)
                val isSaveChatUser = saveChat(idUser, idFriend, chatUser)
                val isSaveChatFriend = saveChat(idFriend, idUser, chatFriend)

                if (!isSaveChatUser || !isSaveChatFriend) Toast.makeText(
                    applicationContext,
                    "A",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        etMessage.setText("")
    }

    private fun saveChat(sender: String, receiver: String?, chat: Chat) =
        SaveChat().save(sender, receiver, chat)

    private fun initToolbar(title: String?) {
        toolbarChat.title = title
        setSupportActionBar(toolbarChat)
        toolbarChat.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbarChat.setNavigationOnClickListener{ finish() }
    }

}
