import React from 'react';
import Card from '../components/Card';
import CrowdTable from '../components/CrowdTable';

const CrowdPage = () => {
  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-2xl font-bold text-slate-800">Crowd Density Prediction</h1>
        <p className="text-slate-500 mt-1">Monitor real-time and predicted crowd levels across venue zones.</p>
      </div>
      
      <Card>
        <CrowdTable />
      </Card>
    </div>
  );
};

export default CrowdPage;
