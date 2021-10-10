import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../services/dashboard.service';
import { Chart, registerables } from 'chart.js';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private dashboardService: DashboardService) {
    Chart.register(...registerables);
  }

  tempMax: any[];
  tempMin: any[];
  dates: any[];
  chart: any = [];

  ngOnInit(): void {
    this.dashboardService.getAllUsers().subscribe(data => {
      let tempMax = data['list']
        .map(data => data.main.temp_max);

      let tempMin = data['list']
        .map(data => data.main.temp_min);

      let dates = data['list']
        .map(data => data.dt)

      let weatherDates = [];
      dates.forEach(el => {
        let jsDate = new Date(el * 1000);
        weatherDates.push(jsDate.toLocaleTimeString('en'), {
          year: 'numeric',
          month: 'short',
          day: 'numeric'
        })
      });

      this.tempMax = tempMax;
      this.tempMin = tempMin;
      this.dates = weatherDates;

      this.drawChart();
    });
  }

  drawChart() {
    this.chart = new Chart('canvas', {
      type: 'line',
      data: {
        labels: this.dates,
        datasets: [
          {
            label: 'Max temperature',
            data: this.tempMax,
            borderColor: '#007bff',
            fill: false
          },
          {
            label: 'Min temperature',
            data: this.tempMin,
            borderColor: '#ffcc00',
            fill: true
          },
        ]
      },
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: 'Weather chart for Employee Management System'
          }
        },
        scales: {
          x: {
            display: true,
            title: {
              display: true,
              text: 'Dates'
            }
          },
          y: {
            display: true,
          }
        }
      }
    })
  }
}
