import React from 'react';
import Card from '../components/Card';
import ChatBox from '../components/ChatBox';

const AssistantPage = () => {
  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-2xl font-bold text-slate-800">AI Assistant</h1>
        <p className="text-slate-500 mt-1">Ask questions to find the best routes and areas in the venue.</p>
      </div>
      
      <div className="max-w-3xl mx-auto">
        <ChatBox />
      </div>
    </div>
  );
};

export default AssistantPage;
