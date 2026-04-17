import React, { useState, useRef, useEffect } from 'react';
import axios from 'axios';
import { Send, Bot, User } from 'lucide-react';

const ChatBox = () => {
  const [messages, setMessages] = useState([
    { id: 1, text: 'Hello! I am your CrowdFlow AI Assistant. Ask me about the fastest gates, least crowded areas, or nearest exits.', sender: 'bot' }
  ]);
  const [input, setInput] = useState('');
  const [loading, setLoading] = useState(false);
  const messagesEndRef = useRef(null);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  };

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!input.trim()) return;

    const userMessage = { id: Date.now(), text: input, sender: 'user' };
    setMessages(prev => [...prev, userMessage]);
    setInput('');
    setLoading(true);

    try {
      const response = await axios.post('https://crowdflow-ai-885469863630.asia-south1.run.app/api/assistant', { query: userMessage.text });
      const botMessage = { id: Date.now() + 1, text: response.data.data.response, sender: 'bot' };
      setMessages(prev => [...prev, botMessage]);
    } catch (error) {
      console.error('Error getting assistant response', error);
      const errorMessage = { id: Date.now() + 1, text: 'Sorry, I am having trouble connecting to the server right now.', sender: 'bot' };
      setMessages(prev => [...prev, errorMessage]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex flex-col h-[500px]">
      <div className="flex-1 overflow-y-auto p-4 space-y-4 bg-slate-50 rounded-t-xl border border-slate-200 border-b-0">
        {messages.map((msg) => (
          <div key={msg.id} className={`flex ${msg.sender === 'user' ? 'justify-end' : 'justify-start'}`}>
            <div className={`flex gap-3 max-w-[80%] ${msg.sender === 'user' ? 'flex-row-reverse' : ''}`}>
              <div className={`w-8 h-8 rounded-full flex items-center justify-center flex-shrink-0 ${msg.sender === 'user' ? 'bg-blue-600 text-white' : 'bg-white border border-slate-200 text-blue-600'}`}>
                {msg.sender === 'user' ? <User size={16} /> : <Bot size={16} />}
              </div>
              <div className={`px-4 py-2.5 rounded-2xl ${
                msg.sender === 'user' 
                  ? 'bg-blue-600 text-white rounded-tr-sm' 
                  : 'bg-white border border-slate-200 text-slate-800 rounded-tl-sm shadow-sm'
              }`}>
                <p className="text-sm">{msg.text}</p>
              </div>
            </div>
          </div>
        ))}
        {loading && (
          <div className="flex justify-start">
            <div className="flex gap-3 max-w-[80%]">
              <div className="w-8 h-8 rounded-full bg-white border border-slate-200 text-blue-600 flex items-center justify-center flex-shrink-0">
                <Bot size={16} />
              </div>
              <div className="px-4 py-3 rounded-2xl bg-white border border-slate-200 rounded-tl-sm shadow-sm flex items-center gap-1">
                <div className="w-2 h-2 bg-slate-400 rounded-full animate-bounce"></div>
                <div className="w-2 h-2 bg-slate-400 rounded-full animate-bounce" style={{ animationDelay: '0.2s' }}></div>
                <div className="w-2 h-2 bg-slate-400 rounded-full animate-bounce" style={{ animationDelay: '0.4s' }}></div>
              </div>
            </div>
          </div>
        )}
        <div ref={messagesEndRef} />
      </div>
      <form onSubmit={handleSubmit} className="p-3 bg-white border border-slate-200 rounded-b-xl flex gap-2">
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Ask me anything..."
          className="flex-1 px-4 py-2 bg-slate-100 border-transparent rounded-lg focus:bg-white focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all"
        />
        <button
          type="submit"
          disabled={loading || !input.trim()}
          className="p-2.5 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors disabled:opacity-50 flex items-center justify-center"
        >
          <Send size={18} />
        </button>
      </form>
    </div>
  );
};

export default ChatBox;
