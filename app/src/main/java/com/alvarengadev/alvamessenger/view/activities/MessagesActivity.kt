package com.alvarengadev.alvamessenger.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.Chat
import com.alvarengadev.alvamessenger.presenter.chat.save.SaveChatPresenter
import com.alvarengadev.alvamessenger.presenter.messages.save.SaveMessagePresenter
import com.alvarengadev.alvamessenger.presenter.messages.list.ListMessagesInterface
import com.alvarengadev.alvamessenger.presenter.messages.list.ListMessagesPresenter
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.alvarengadev.alvamessenger.utils.Constants
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.alvarengadev.alvamessenger.utils.RoutesUtils
import kotlinx.android.synthetic.main.activity_chat.*

class MessagesActivity : AppCompatActivity(), ListMessagesInterface.View {

    private lateinit var listMessagesPresenter: ListMessagesPresenter
    private lateinit var emailFriend: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val bundle = intent.extras!!

        listMessagesPresenter = ListMessagesPresenter(this)
        emailFriend = bundle.getString(Constants.FRIEND_EMAIL, "")
        val nameFriend = bundle.getString(Constants.FRIEND_NAME, "")

        initToolbar(nameFriend)
        initListMessages()
        fabSendMessage.setOnClickListener { sendMessage(nameFriend, emailFriend, getInput()) }
    }

    private fun getInput() = etMessage.text.toString()

    private fun initListMessages() {
        rcyChat.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@MessagesActivity)
            adapter = listMessagesPresenter.getAdapter()
        }
    }

    private fun sendMessage(nameFriend: String?, friendEmail: String?, message: String) {
        val preferences = PreferencesUtils(this)
        val idUser = preferences.getUserKey()
        val nameUser = preferences.getUserName()

        val idFriend = Base64Actions.encodeBase64(friendEmail!!)
        val saveMessage = SaveMessagePresenter(this@MessagesActivity)

        if (message.trim().isNotEmpty()) {

            val isSaveMessageUser = saveMessage.save(idUser!!, idFriend, message)
            val isSaveMessageFriend = saveMessage.save(idFriend, idUser, message)

            if (!isSaveMessageFriend && !isSaveMessageUser) {
                Toast.makeText(
                    this@MessagesActivity,
                    "Erro ao salvar a mensagem",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val chatUser = Chat(idFriend, nameFriend!!, message)
                val chatFriend = Chat(idUser, nameUser!!, message)
                val isSaveChatUser = saveChat(idUser, idFriend, chatUser)
                val isSaveChatFriend = saveChat(idFriend, idUser, chatFriend)

                if (!isSaveChatUser && !isSaveChatFriend) Toast.makeText(
                    this@MessagesActivity,
                    "Erro ao salvar a mensagem",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        etMessage.setText("")
    }

    private fun saveChat(sender: String, receiver: String?, chat: Chat) =
        SaveChatPresenter().save(sender, receiver, chat)

    private fun initToolbar(title: String?) {
        toolbarChat.title = title
        setSupportActionBar(toolbarChat)
        toolbarChat.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbarChat.setNavigationOnClickListener { returnHome() }
    }

    private fun returnHome() {
        startActivity(RoutesUtils.routes(this@MessagesActivity, HomeActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        returnHome()
    }

    override fun userKey(): String? = PreferencesUtils(this@MessagesActivity).getUserKey()

    override fun friendKey(): String? = Base64Actions.encodeBase64(emailFriend)

    override fun error(message: String) =
        Toast.makeText(this@MessagesActivity, message, Toast.LENGTH_SHORT).show()

    override fun onStop() {
        super.onStop()
        listMessagesPresenter.stopGetMessages()
    }
}
