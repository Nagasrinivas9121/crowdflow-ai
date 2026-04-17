import React, { useState } from 'react';
import axios from 'axios';
import { Calculator } from 'lucide-react';

const WaitTimeForm = () => {
  const [formData, setFormData] = useState({
    people: 50,
    serviceTime: 2,
    counters: 3
  });
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: parseInt(e.target.value) || 0 });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await axios.post('https://crowdflow-ai-885469863630.asia-south1.run.app/api/wait-time', formData);
      setResult(response.data.data);
    } catch (error) {
      console.error('Error calculating wait time', error);
    } finally {
      setLoading(false);
    }
  };

  const getRecColor = (rec) => {
    switch (rec) {
      case 'FAST': return 'text-green-600 bg-green-50 border-green-200';
      case 'MODERATE': return 'text-yellow-600 bg-yellow-50 border-yellow-200';
      case 'SLOW': return 'text-red-600 bg-red-50 border-red-200';
      default: return 'text-slate-600 bg-slate-50 border-slate-200';
    }
  };

  return (
    <div className="grid md:grid-cols-2 gap-8">
      <div>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-slate-700 mb-1">Number of People in Queue</label>
            <input 
              type="number" 
              name="people" 
              value={formData.people} 
              onChange={handleChange}
              className="w-full px-4 py-2 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all"
              required
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-slate-700 mb-1">Service Time per Person (minutes)</label>
            <input 
              type="number" 
              name="serviceTime" 
              value={formData.serviceTime} 
              onChange={handleChange}
              className="w-full px-4 py-2 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all"
              required
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-slate-700 mb-1">Number of Active Counters</label>
            <input 
              type="number" 
              name="counters" 
              value={formData.counters} 
              onChange={handleChange}
              min="1"
              className="w-full px-4 py-2 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all"
              required
            />
          </div>
          <button 
            type="submit" 
            disabled={loading}
            className="w-full flex items-center justify-center gap-2 bg-blue-600 hover:bg-blue-700 text-white font-medium py-2.5 rounded-lg transition-colors disabled:opacity-70"
          >
            <Calculator size={18} />
            {loading ? 'Calculating...' : 'Calculate Wait Time'}
          </button>
        </form>
      </div>

      <div className="flex flex-col justify-center">
        {result ? (
          <div className={`p-6 rounded-xl border text-center ${getRecColor(result.recommendation)}`}>
            <p className="text-sm font-medium mb-2 uppercase tracking-wider opacity-80">Estimated Wait Time</p>
            <div className="text-5xl font-bold mb-4">{result.waitTime.toFixed(1)} <span className="text-2xl font-medium opacity-80">min</span></div>
            <div className="inline-block px-4 py-1.5 rounded-full bg-white/60 text-sm font-bold border border-current">
              Status: {result.recommendation}
            </div>
          </div>
        ) : (
          <div className="h-full flex flex-col items-center justify-center p-6 text-center border-2 border-dashed border-slate-200 rounded-xl text-slate-400">
            <Calculator size={48} className="mb-4 opacity-50" />
            <p>Enter parameters and calculate to see estimated wait times</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default WaitTimeForm;
