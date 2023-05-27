import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  BarChart,
  Tooltip,
  Bar,
  PieChart,
  Pie,
  Treemap,
  AreaChart,
  CartesianGrid,
  Area,
} from "recharts";
import React from "react";
import { makeStyles } from "tss-react/mui";

const ExampleChart = () => {
  const { classes } = useStyles();

  const data = [
    { name: "Apple", price: 400, units: 1007 },
    { name: "Bananas", price: 111, units: 3211 },
    { name: "Strawberies", price: 2222, units: 1600 },
  ];

  return (
    <div className={classes.chartsContainer}>
      {/* The 'dataKey' attribute selects the values from the 'data' object */}
      <div className={classes.chart}>
        <h6>Prices of fruits bought for this experiment</h6>
        <LineChart width={400} height={400} data={data}>
          <XAxis dataKey="name" />
          <YAxis />
          <Line type="monotone" dataKey="price" stroke="#8884d8" />
        </LineChart>
      </div>

      <div className={classes.chart}>
        <h6>How many units bought</h6>
        <LineChart width={400} height={400} data={data}>
          <XAxis dataKey="name" />
          <YAxis />
          <Line type="monotone" dataKey="units" stroke="#8884d8" />
        </LineChart>
      </div>

      <BarChart width={730} height={250} data={data}>
        <XAxis dataKey="name" />
        <YAxis />
        <Tooltip />
        <Bar dataKey="price" fill="#82ca9d" />
        <Bar dataKey="units" fill="#8884d8" />
      </BarChart>

      <PieChart width={730} height={250}>
        <Pie
          data={data}
          dataKey="units"
          nameKey="name"
          cx="50%"
          cy="50%"
          outerRadius={50}
          fill="#8884d8"
        />
        <Pie
          data={data}
          dataKey="price"
          nameKey="name"
          cx="50%"
          cy="50%"
          innerRadius={60}
          outerRadius={80}
          fill="#82ca9d"
          label
        />
        <Tooltip />
      </PieChart>

      <Treemap
        width={730}
        height={250}
        data={data}
        dataKey="units"
        aspectRatio={4 / 3}
        stroke="#fff"
        fill="#82ca9d"
      />

      <AreaChart
        width={730}
        height={250}
        data={data}
        margin={{ top: 10, right: 30, left: 0, bottom: 0 }}
      >
        <defs>
          <linearGradient id="colorUv" x1="0" y1="0" x2="0" y2="1">
            <stop offset="5%" stopColor="#8884d8" stopOpacity={0.8} />
            <stop offset="95%" stopColor="#8884d8" stopOpacity={0} />
          </linearGradient>
          <linearGradient id="colorPv" x1="0" y1="0" x2="0" y2="1">
            <stop offset="5%" stopColor="#82ca9d" stopOpacity={0.8} />
            <stop offset="95%" stopColor="#82ca9d" stopOpacity={0} />
          </linearGradient>
        </defs>
        <XAxis dataKey="name" />
        <YAxis />
        <CartesianGrid strokeDasharray="3 3" />
        <Tooltip />
        <Area
          type="monotone"
          dataKey="units"
          stroke="#8884d8"
          fillOpacity={1}
          fill="url(#colorUv)"
        />
        <Area
          type="monotone"
          dataKey="price"
          stroke="#82ca9d"
          fillOpacity={1}
          fill="url(#colorPv)"
        />
      </AreaChart>
    </div>
  );
};

const useStyles = makeStyles()(() => ({
  chartsContainer: {
    display: "flex",
    flexDirection: "row",
    flexWrap: "wrap",
    gap: "1rem",
  },
  chart: {
    background: "white",
  },
}));

export default ExampleChart;
