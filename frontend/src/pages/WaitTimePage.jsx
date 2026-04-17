import React from 'react';
import Card from '../components/Card';
import WaitTimeForm from '../components/WaitTimeForm';

const WaitTimePage = () => {
  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-2xl font-bold text-slate-800">Wait Time Prediction</h1>
        <p className="text-slate-500 mt-1">Calculate the estimated wait time for any queue based on simple metrics.</p>
      </div>
      
      <Card>
        <WaitTimeForm />
      </Card>
    </div>
  );
};

export default WaitTimePage;
