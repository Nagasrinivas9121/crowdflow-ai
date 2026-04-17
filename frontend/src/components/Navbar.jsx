import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { Activity, Users, Clock, MessageSquare } from 'lucide-react';

const Navbar = () => {
  const location = useLocation();
  
  const links = [
    { name: 'Dashboard', path: '/', icon: <Activity size={18} /> },
    { name: 'Crowd Density', path: '/crowd', icon: <Users size={18} /> },
    { name: 'Wait Time', path: '/wait-time', icon: <Clock size={18} /> },
    { name: 'AI Assistant', path: '/assistant', icon: <MessageSquare size={18} /> },
  ];

  return (
    <nav className="bg-white border-b border-slate-200 sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16">
          <div className="flex items-center gap-2">
            <div className="w-8 h-8 rounded-lg bg-blue-500 flex items-center justify-center text-white font-bold">
              CF
            </div>
            <Link to="/" className="text-xl font-bold text-slate-800">
              CrowdFlow <span className="text-blue-500">AI</span>
            </Link>
          </div>
          <div className="hidden sm:flex sm:items-center sm:space-x-8">
            {links.map((link) => (
              <Link
                key={link.name}
                to={link.path}
                className={`flex items-center gap-2 px-3 py-2 text-sm font-medium transition-colors border-b-2 ${
                  location.pathname === link.path
                    ? 'border-blue-500 text-blue-600'
                    : 'border-transparent text-slate-500 hover:text-slate-800 hover:border-slate-300'
                }`}
              >
                {link.icon}
                {link.name}
              </Link>
            ))}
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
