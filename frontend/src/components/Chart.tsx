import {
  XAxis,
  YAxis,
  BarChart,
  Tooltip,
  Bar, LineChart, Line, Pie, PieChart, Cell, LabelList, Legend,
} from "recharts";
import React, {useEffect, useState} from "react";
import { makeStyles } from "tss-react/mui";
import statisticsService from "../services/statistics-service";

const Chart = () => {
  const {classes} = useStyles();
  const [revenueByGenreData, setRevenueByGenreData] = useState([]);
  const [avgRevenueByYear, setAvgRevenueByYear] = useState([]);
  const [avgBudgetByYear, setAvgBudgetByYear] = useState([]);
  const [languages, setLanguages] = useState([]);

  useEffect(() => {
    async function call() {
      setRevenueByGenreData(await statisticsService.getGenreRevenueStatistics() as []);
      setAvgRevenueByYear(await statisticsService.getAvgRevenueByYear() as [])
      setAvgBudgetByYear(await statisticsService.getAvgBudgetByYear() as [])
      setLanguages(await statisticsService.getMovieLanguages() as [])
    }
    call()
  }, []);

  var COLORS = [
    "#00FF00", // Green
    "#0000FF", // Blue
    "#FFFF00", // Yellow
    "#FF00FF", // Magenta
    "#00FFFF", // Cyan
    "#FFA500", // Orange
    "#800080", // Purple
    "#008000", // Dark Green
    "#800000", // Maroon
    "#000080", // Navy
    "#FFC0CB", // Pink
    "#A52A2A", // Brown
    "#008080", // Teal
    "#FFD700", // Gold
    "#000000", // Black
    "#FFFFFF", // White
    "#808080", // Gray
    "#DC143C", // Crimson
    "#F0E68C", // Khaki
    "#FF0000", // Red
    "#7FFF00", // Chartreuse
    "#FF69B4", // Hot Pink
    "#00CED1", // Dark Turquoise
    "#D2691E", // Chocolate
    "#BDB76B", // Dark Khaki
    "#1E90FF", // Dodger Blue
    "#32CD32", // Lime Green
    "#9370DB", // Medium Purple
    "#F08080", // Light Coral
    "#00FF7F"  // Spring Green
  ];


  return (
      <>
        <div>
          <h2 style={{color: 'white'}}>Total Revenue By Genre</h2>
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
          <h2 style={{color: 'white'}}>Average Movie Revenue by Year</h2>
          <div className={classes.chartsContainer}>
            <LineChart width={1400} height={400} data={avgRevenueByYear}>
              <XAxis dataKey="x"/>
              <YAxis width={120} orientation={'left'} name={'Revenue'} unit={"$"}/>
              <Line type="monotone" dataKey="y" stroke="#8884d8"/>
            </LineChart>
          </div>
        </div>
        <div>
          <h2 style={{color: 'white'}}>Average Movie Budget by Year</h2>
          <div className={classes.chartsContainer}>
            <LineChart width={1400} height={400} data={avgBudgetByYear}>
              <XAxis dataKey="x"/>
              <YAxis width={120} orientation={'left'} name={'Revenue'} unit={"$"}/>
              <Line type="monotone" dataKey="y" stroke="#8884d8"/>
            </LineChart>
          </div>
        </div>
        <div >
         <h2 style={{color: 'white'}}>Movie Languages</h2>
            <PieChart width={700} height={700}>
              <Pie
                data={languages}
                dataKey="y"
                nameKey="x"
                cx="50%"
                cy="50%"
                outerRadius={300}
                fill="#8884d8">
                {
                  languages.map((entry, index) => <Cell fill={COLORS[index % COLORS.length]}/>)
                }
                <LabelList
                    dataKey="x"
                    position="inside"
                    style={{ fontSize: "15px" }}
                />
                </Pie>
              <Legend layout="vertical" verticalAlign="middle" align="right" />
              <Tooltip />
            </PieChart>
        </div>
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
