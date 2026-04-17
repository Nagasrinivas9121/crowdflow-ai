import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Dashboard from './pages/Dashboard';
import CrowdPage from './pages/CrowdPage';
import WaitTimePage from './pages/WaitTimePage';
import AssistantPage from './pages/AssistantPage';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-slate-50 font-sans text-slate-900 flex flex-col">
        <Navbar />
        <main className="flex-1 max-w-7xl w-full mx-auto px-4 sm:px-6 lg:px-8 py-8">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/crowd" element={<CrowdPage />} />
            <Route path="/wait-time" element={<WaitTimePage />} />
            <Route path="/assistant" element={<AssistantPage />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
