package com.example.projeeeeeeeeeect; // i didn't move it to user
import com.example.projeeeeeeeeeect.network.ApiService;
import com.example.projeeeeeeeeeect.network.RetrofitClient;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log; // Import Log
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import com.sendbird.android.channel.GroupChannel;
//import com.sendbird.android.handler.GroupChannelHandler;
//import com.sendbird.android.message.BaseMessage;

import com.example.projeeeeeeeeeect.Adapters.ChatAdapter;
import com.example.projeeeeeeeeeect.Models.Message;
// Import all the new classes
import com.example.projeeeeeeeeeect.Models.ChatStartRequest;
import com.example.projeeeeeeeeeect.Models.ChatStartResponse;
import com.example.projeeeeeeeeeect.Models.SendMessageRequest;
import com.example.projeeeeeeeeeect.Models.SendMessageResponse;
import com.example.projeeeeeeeeeect.Models.SendBirdMessage; // Import this new model

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Conversation extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText etMessage;
    private Button btnSend;
    private ChatAdapter chatAdapter;
    private List<Message> messageList;

    // Variables to hold the chat session info
    private String sessionToken;
    private String channelUrl;

    // We'll need the API service
    private ApiService apiService;

    // IDs passed from the previous activity
    // TODO: You MUST get these IDs from your SessionManager or the Intent
    private int currentUserId = 1; // Example: get from SessionManager
    private String currentChatUserId = "survivor_2"; // Example: get from SessionManager
    private int counselorId = 2; // Example: get from Intent
    private int reportId = 1;    // Example: get from Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        // Get the API service (passing context)
        apiService = RetrofitClient.getApiService(this);

        // TODO: Get real data passed from the previous activity
        // counselorId = getIntent().getIntExtra("COUNSELOR_ID", -1);
        // reportId = getIntent().getIntExtra("REPORT_ID", -1);
        // String counselorName = getIntent().getStringExtra("counselorName");
        // setTitle("Chat with " + counselorName);

        recyclerView = findViewById(R.id.recyclerChat);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        // Start the chat session
        startChatSession();

        btnSend.setOnClickListener(v -> {
            String messageText = etMessage.getText().toString().trim();
            if (TextUtils.isEmpty(messageText)) {
                return;
            }

            // Check if chat session is ready
            if (TextUtils.isEmpty(sessionToken) || TextUtils.isEmpty(channelUrl)) {
                Toast.makeText(this, "Chat session not ready, please wait.", Toast.LENGTH_SHORT).show();
                return;
            }

            // --- THIS IS THE NEW LOGIC ---

            // 1. Create the local Message object
            Message newMessage = new Message(messageText, true, System.currentTimeMillis());

            // 2. Add it to the UI immediately (optimistic update)
            messageList.add(newMessage);
            chatAdapter.notifyItemInserted(messageList.size() - 1);
            recyclerView.scrollToPosition(messageList.size() - 1);
            etMessage.setText(""); // Clear the input

            // 3. Send the message to the API in the background
            sendMessageToApi(messageText, newMessage);
        });
    }

    private void startChatSession() {
        // Show a loading message
        etMessage.setEnabled(false);
        etMessage.setHint("Starting chat session...");

        ChatStartRequest request = new ChatStartRequest(currentUserId, counselorId, reportId);

        apiService.startChat(request).enqueue(new Callback<ChatStartResponse>() {
            @Override
            public void onResponse(Call<ChatStartResponse> call, Response<ChatStartResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Save session info
                    sessionToken = response.body().sessionToken;
                    channelUrl = response.body().channelUrl;

                    etMessage.setEnabled(true);
                    etMessage.setHint("Type a message...");
                    Toast.makeText(Conversation.this, "Chat started!", Toast.LENGTH_SHORT).show();

                    // TODO: You would also fetch previous messages here

                } else {
                    Toast.makeText(Conversation.this, "Failed to start chat.", Toast.LENGTH_SHORT).show();
                    etMessage.setHint("Chat failed. Please go back.");
                }
            }

            @Override
            public void onFailure(Call<ChatStartResponse> call, Throwable t) {
                Toast.makeText(Conversation.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This method sends the message to the API
     * It no longer adds the message to the UI, it just confirms the send.
     */
    private void sendMessageToApi(String messageText, Message messageObject) {

        SendMessageRequest request = new SendMessageRequest(sessionToken, channelUrl, messageText);

        apiService.sendMessage(request).enqueue(new Callback<SendMessageResponse>() {
            @Override
            public void onResponse(Call<SendMessageResponse> call, Response<SendMessageResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().message != null) {
                    // Success! The server confirmed the message.
                    SendBirdMessage confirmedMsg = response.body().message;

                    // We can log the confirmation
                    Log.d("Conversation", "Message sent and confirmed. ID: " + confirmedMsg.messageId);

                    // TODO: You could update the 'Message' object with the real timestamp
                    // or a "sent" checkmark here.

                } else {
                    // The send failed
                    Toast.makeText(Conversation.this, "Failed to send message.", Toast.LENGTH_SHORT).show();
                    // TODO: You could add a "failed" icon to the 'messageObject' here
                }
            }

            @Override
            public void onFailure(Call<SendMessageResponse> call, Throwable t) {
                // The network failed
                Toast.makeText(Conversation.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                // TODO: You could also mark the message as "failed" here
            }
        });
    }

    // TODO: You will also need a method to *receive* messages.
    // This typically involves a WebSocket or polling the server every few seconds.
    // The code here only *sends* messages.
}