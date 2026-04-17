import React from 'react';
import { Link } from 'react-router-dom';
import { Users, Clock, MessageSquare, ArrowRight } from 'lucide-react';
import Card from '../components/Card';

const Dashboard = () => {
  const modules = [
    {
      title: 'Crowd Density',
      description: 'View real-time and predicted crowd density across different venue zones.',
      icon: <Users size={24} className="text-blue-500" />,
      path: '/crowd',
      color: 'bg-blue-50'
    },
    {
      title: 'Wait Time estimation',
      description: 'Calculate queue wait times based on people and active service counters.',
      icon: <Clock size={24} className="text-purple-500" />,
      path: '/wait-time',
      color: 'bg-purple-50'
    },
    {
      title: 'AI Assistant',
      description: 'Ask questions to find the fastest gates, best food stalls, and nearest exits.',
      icon: <MessageSquare size={24} className="text-green-500" />,
      path: '/assistant',
      color: 'bg-green-50'
    }
  ];

  return (
    <div className="space-y-6">
      <div className="bg-gradient-to-r from-blue-600 to-blue-800 rounded-2xl p-8 text-white shadow-md">
        <h1 className="text-3xl font-bold mb-3">Welcome to CrowdFlow AI</h1>
        <p className="text-blue-100 max-w-2xl text-lg">
          The smart physical event experience platform. Monitor crowd density, optimize your queue times, and make intelligent decisions to improve your event experience.
        </p>
      </div>

      <h2 className="text-xl font-bold text-slate-800 px-1 pt-4">Explore Modules</h2>
      
      <div className="grid md:grid-cols-3 gap-6">
        {modules.map((mod) => (
          <Link key={mod.title} to={mod.path} className="group">
            <Card className="h-full hover:shadow-md transition-shadow border-transparent hover:border-slate-200">
              <div className={`w-12 h-12 rounded-xl ${mod.color} flex items-center justify-center mb-4`}>
                {mod.icon}
              </div>
              <h3 className="text-lg font-bold text-slate-800 mb-2">{mod.title}</h3>
              <p className="text-slate-500 mb-4 text-sm">{mod.description}</p>
              <div className="flex items-center text-sm font-medium text-blue-600 group-hover:translate-x-1 transition-transform">
                Open Module <ArrowRight size={16} className="ml-1" />
              </div>
            </Card>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default Dashboard;
