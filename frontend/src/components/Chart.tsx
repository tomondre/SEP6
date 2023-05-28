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
  const [releases, setReleases] = useState([]);

  useEffect(() => {
    async function call() {
      setRevenueByGenreData(await statisticsService.getGenreRevenueStatistics() as []);
      setAvgRevenueByYear(await statisticsService.getAvgRevenueByYear() as [])
      setAvgBudgetByYear(await statisticsService.getAvgBudgetByYear() as [])
      setLanguages(await statisticsService.getMovieLanguages() as [])
      setReleases(await statisticsService.getNoOfReleasesByYear() as [])
    }
    call()
  }, []);

  var COLORS = [
    "#8884d8", // Green
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
                <Bar dataKey="y" fill="#8884d8"/>
              </BarChart>
            </div>
        </div>
        <div>
          <h2 style={{color: 'white'}}>Average Movie Revenue by Year</h2>
          <div className={classes.chartsContainer}>
            <LineChart width={1400} height={400} data={avgRevenueByYear}>
              <XAxis dataKey="x"/>
              <YAxis width={120} orientation={'left'} name={'Revenue'} unit={"$"}/>
              <Line strokeWidth={8} type="monotone" dataKey="y" stroke="#8884d8"/>
            </LineChart>
          </div>
        </div>
        <div>
          <h2 style={{color: 'white'}}>Average Movie Budget by Year</h2>
          <div className={classes.chartsContainer}>
            <LineChart width={1400} height={400} data={avgBudgetByYear}>
              <XAxis dataKey="x"/>
              <YAxis width={120} orientation={'left'} name={'Revenue'} unit={"$"}/>
              <Line strokeWidth={8} type="monotone" dataKey="y" stroke="#8884d8"/>
            </LineChart>
          </div>
        </div>
        <div>
          <h2 style={{color: 'white'}}>Number of Releases by Year</h2>
          <div className={classes.chartsContainer}>
            <LineChart width={1400} height={400} data={releases}>
              <XAxis dataKey="x"/>
              <YAxis width={120} orientation={'left'} name={'Revenue'}/>
              <Line strokeWidth={8} type="monotone" width={20} dataKey="y" stroke="#8884d8"/>
            </LineChart>
          </div>
        </div>
        <div >
         <h2 style={{color: 'white'}}>Movie Languages</h2>
         <div className={classes.pieChart}>
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
        </div>
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
  pieChart: {
    display: "flex",
    justifyContent: "center",
  }
}));

export default Chart;
