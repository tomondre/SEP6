import {
  XAxis,
  YAxis,
  BarChart,
  Tooltip,
  Bar, LineChart, Line,
} from "recharts";
import React, {useEffect, useState} from "react";
import { makeStyles } from "tss-react/mui";
import statisticsService from "../services/statistics-service";

const Chart = () => {
  const {classes} = useStyles();
  const [revenueByGenreData, setRevenueByGenreData] = useState([]);
  const [avgRevenueByYear, setAvgRevenueByYear] = useState([]);

  useEffect(() => {
    async function call() {
      setRevenueByGenreData(await statisticsService.getGenreRevenueStatistics() as []);
      setAvgRevenueByYear(await statisticsService.getAvgRevenueByYear() as [])
    }
    call()
  }, []);

  return (
      <>
        <div>
          <h2 style={{color: 'white'}}>Revenue By Genre</h2>
            <div className={classes.chartsContainer}>
              <BarChart width={1400} height={400} data={revenueByGenreData}>
                <XAxis tick={{fontSize: 15}} dataKey="x"/>
                <YAxis width={120} orientation={'left'} name={'Revenue'} unit={"$"}/>
                <Tooltip/>
                <Bar dataKey="y" fill="#82ca9d"/>
              </BarChart>
            </div>
        </div>
        <div>
          <h2 style={{color: 'white'}}>Average Revenue by year</h2>
          <div className={classes.chart}>
            <LineChart width={1400} height={400} data={avgRevenueByYear}>
              <XAxis dataKey="x"/>
              <YAxis/>
              <Line type="monotone" dataKey="y" stroke="#8884d8"/>
            </LineChart>
          </div>
        </div>

            {/*<PieChart width={730} height={250}>*/}
            {/*  <Pie*/}
            {/*    data={data}*/}
            {/*    dataKey="units"*/}
            {/*    nameKey="x"*/}
            {/*    cx="50%"*/}
            {/*    cy="50%"*/}
            {/*    outerRadius={50}*/}
            {/*    fill="#8884d8"*/}
            {/*  />*/}
            {/*  <Tooltip />*/}
            {/*</PieChart>*/}

            {/*<Treemap*/}
            {/*    width={730}*/}
            {/*    height={250}*/}
            {/*    data={data}*/}
            {/*    dataKey="y"*/}
            {/*    aspectRatio={4 / 3}*/}
            {/*    stroke="#fff"*/}
            {/*    fill="#82ca9d"*/}
            {/*/>*/}

            {/*<AreaChart*/}
            {/*    width={730}*/}
            {/*    height={250}*/}
            {/*    data={data}*/}
            {/*    margin={{top: 10, right: 30, left: 0, bottom: 0}}*/}
            {/*>*/}
            {/*  <defs>*/}
            {/*    <linearGradient id="colorUv" x1="0" y1="0" x2="0" y2="1">*/}
            {/*      <stop offset="5%" stopColor="#8884d8" stopOpacity={0.8}/>*/}
            {/*      <stop offset="95%" stopColor="#8884d8" stopOpacity={0}/>*/}
            {/*    </linearGradient>*/}
            {/*    <linearGradient id="colorPv" x1="0" y1="0" x2="0" y2="1">*/}
            {/*      <stop offset="5%" stopColor="#82ca9d" stopOpacity={0.8}/>*/}
            {/*      <stop offset="95%" stopColor="#82ca9d" stopOpacity={0}/>*/}
            {/*    </linearGradient>*/}
            {/*  </defs>*/}
            {/*  <XAxis dataKey="x"/>*/}
            {/*  <YAxis/>*/}
            {/*  <CartesianGrid strokeDasharray="3 3"/>*/}
            {/*  <Tooltip/>*/}
            {/*  <Area*/}
            {/*      type="monotone"*/}
            {/*      dataKey="y"*/}
            {/*      stroke="#8884d8"*/}
            {/*      fillOpacity={1}*/}
            {/*      fill="url(#colorUv)"*/}
            {/*  />*/}
            {/*  <Area*/}
            {/*      type="monotone"*/}
            {/*      dataKey="y"*/}
            {/*      stroke="#82ca9d"*/}
            {/*      fillOpacity={1}*/}
            {/*      fill="url(#colorPv)"*/}
            {/*  />*/}
            {/*</AreaChart>*/}
        </>
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

export default Chart;
