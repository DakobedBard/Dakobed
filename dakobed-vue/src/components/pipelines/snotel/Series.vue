<template>
  <div id="app">
      <h1>
          Series
      </h1>
              <svg class="line-chart"></svg>
        

  </div>
</template>


<script>
// import BarChart from "../../d3charts/components/BarChart.vue";
import * as d3 from 'd3'

export default {
  
  methods:{
    parseData(data) {
        var arr = [];
        for (var i in data.bpi) {
            arr.push({
                date: new Date(i), //date
                value: +data.bpi[i] //convert string to number
            });
        }
        return arr;
    },
    drawChart(data) {
    var svgWidth = 600, svgHeight = 400;
    var margin = { top: 20, right: 20, bottom: 30, left: 50 };
    var width = svgWidth - margin.left - margin.right;
    var height = svgHeight - margin.top - margin.bottom;

    var svg = d3.select('svg')
        .attr("width", svgWidth)
        .attr("height", svgHeight);
        
    var g = svg.append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    var x = d3.scaleTime()
        .rangeRound([0, width]);

    var y = d3.scaleLinear()
        .rangeRound([height, 0]);

    var line = d3.line()
        .x(function(d) { return x(d.date)})
        .y(function(d) { return y(d.value)})
        x.domain(d3.extent(data, function(d) { return d.date }));
        y.domain(d3.extent(data, function(d) { return d.value }));

    g.append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x))
        .select(".domain")
        .remove();

    g.append("g")
        .call(d3.axisLeft(y))
        .append("text")
        .attr("fill", "#000")
        .attr("transform", "rotate(-90)")
        .attr("y", 6)
        .attr("dy", "0.71em")
        .attr("text-anchor", "end")
        .text("Price ($)");

    g.append("path")
        .datum(data)
        .attr("fill", "none")
        .attr("stroke", "steelblue")
        .attr("stroke-linejoin", "round")
        .attr("stroke-linecap", "round")
        .attr("stroke-width", 1.5)
        .attr("d", line);
}

  },
  
  
  components: {
    
  },
  mounted(){
      document.addEventListener("DOMContentLoaded", function(event) {
fetch(this.api)
    .then(function(response) { return response.json(); })
    .then(function(data) {
        var parsedData = this.parseData(data);
        this.drawChart(parsedData);
        console.log(event)
    })
    .catch(function(err) { console.log(err); })
});

    //   d3.select('h1').style('color','red')
    //   var svgWidth = 500, svgHeight = 300, barPadding = 5;
    // var svg = d3.select('svg').attr("width", svgWidth).attr('height',svgHeight)
  },
  data: () => ({
      dataset: [80,100,120,100,80],
      api : 'https://api.coindesk.com/v1/bpi/historical/close.json?start=2017-12-31&end=2018-04-01'
      
  })
};
</script>

<style>
#app {
  font-family: "Open Sans", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #282f36;
  margin-top: 30px;
}
</style>
