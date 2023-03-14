/* eslint-disable object-shorthand */

/* global Chart, CustomTooltips, getStyle, hexToRgba */

/**
 * --------------------------------------------------------------------------
 * CoreUI Free Boostrap Admin Template (v2.0.0): main.js
 * Licensed under MIT (https://coreui.io/license)
 * --------------------------------------------------------------------------
 */

/* eslint-disable no-magic-numbers */
// Disable the on-canvas tooltip
Chart.defaults.global.pointHitDetectionRadius = 1;
Chart.defaults.global.tooltips.enabled = false;
Chart.defaults.global.tooltips.mode = 'index';
Chart.defaults.global.tooltips.position = 'nearest';
Chart.defaults.global.tooltips.custom = CustomTooltips; // eslint-disable-next-line no-unused-vars

var cardChart1 = new Chart($('#card-chart1'), {
	type: 'line',
	data: {
		labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
		datasets: [{
			label: 'My First dataset',
			backgroundColor: getStyle('--primary'),
			borderColor: 'rgba(255,255,255,.55)',
			data: [65, 59, 84, 84, 51, 55, 40]
		}]
	},
	options: {
		maintainAspectRatio: false,
		legend: {
			display: false
		},
		scales: {
			xAxes: [{
				gridLines: {
					color: 'transparent',
					zeroLineColor: 'transparent'
				},
				ticks: {
					fontSize: 2,
					fontColor: 'transparent'
				}
			}],
			yAxes: [{
				display: false,
				ticks: {
					display: false,
					min: 35,
					max: 89
				}
			}]
		},
		elements: {
			line: {
				borderWidth: 1
			},
			point: {
				radius: 4,
				hitRadius: 10,
				hoverRadius: 4
			}
		}
	}
}); // eslint-disable-next-line no-unused-vars

var cardChart2 = new Chart($('#card-chart2'), {
	type: 'line',
	data: {
		labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
		datasets: [{
			label: 'My First dataset',
			backgroundColor: getStyle('--info'),
			borderColor: 'rgba(255,255,255,.55)',
			data: [1, 18, 9, 17, 34, 22, 11]
		}]
	},
	options: {
		maintainAspectRatio: false,
		legend: {
			display: false
		},
		scales: {
			xAxes: [{
				gridLines: {
					color: 'transparent',
					zeroLineColor: 'transparent'
				},
				ticks: {
					fontSize: 2,
					fontColor: 'transparent'
				}
			}],
			yAxes: [{
				display: false,
				ticks: {
					display: false,
					min: -4,
					max: 39
				}
			}]
		},
		elements: {
			line: {
				tension: 0.00001,
				borderWidth: 1
			},
			point: {
				radius: 4,
				hitRadius: 10,
				hoverRadius: 4
			}
		}
	}
}); // eslint-disable-next-line no-unused-vars

var cardChart3 = new Chart($('#card-chart3'), {
	type: 'line',
	data: {
		labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
		datasets: [{
			label: 'My First dataset',
			backgroundColor: 'rgba(255,255,255,.2)',
			borderColor: 'rgba(255,255,255,.55)',
			data: [78, 81, 80, 45, 34, 12, 40]
		}]
	},
	options: {
		maintainAspectRatio: false,
		legend: {
			display: false
		},
		scales: {
			xAxes: [{
				display: false
			}],
			yAxes: [{
				display: false
			}]
		},
		elements: {
			line: {
				borderWidth: 2
			},
			point: {
				radius: 0,
				hitRadius: 10,
				hoverRadius: 4
			}
		}
	}
}); // eslint-disable-next-line no-unused-vars

var cardChart4 = new Chart($('#card-chart4'), {
	type: 'bar',
	data: {
		labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December', 'January', 'February', 'March', 'April'],
		datasets: [{
			label: 'My First dataset',
			backgroundColor: 'rgba(255,255,255,.2)',
			borderColor: 'rgba(255,255,255,.55)',
			data: [78, 81, 80, 45, 34, 12, 40, 85, 65, 23, 12, 98, 34, 84, 67, 82]
		}]
	},
	options: {
		maintainAspectRatio: false,
		legend: {
			display: false
		},
		scales: {
			xAxes: [{
				display: false,
				barPercentage: 0.6
			}],
			yAxes: [{
				display: false
			}]
		}
	}
}); // eslint-disable-next-line no-unused-vars

//var mainChart = new Chart($('#general-production-chart'), {
//  type: 'line',
//  data: {
//    labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S', 'M', 'T', 'W', 'T', 'F', 'S', 'S', 'M', 'T', 'W', 'T', 'F', 'S', 'S', 'M', 'T', 'W', 'T', 'F', 'S', 'S'],
//    datasets: [{
//      label: 'My First dataset',
//      backgroundColor: hexToRgba(getStyle('--info'), 10),
//      borderColor: getStyle('--info'),
//      pointHoverBackgroundColor: '#fff',
//      borderWidth: 2,
//      data: [165, 180, 70, 69, 77, 57, 125, 165, 172, 91, 173, 138, 155, 89, 50, 161, 65, 163, 160, 103, 114, 185, 125, 196, 183, 64, 137, 95, 112, 175]
//    }, {
//      label: 'My Second dataset',
//      backgroundColor: 'transparent',
//      borderColor: getStyle('--success'),
//      pointHoverBackgroundColor: '#fff',
//      borderWidth: 2,
//      data: [92, 97, 80, 100, 86, 97, 83, 98, 87, 98, 93, 83, 87, 98, 96, 84, 91, 97, 88, 86, 94, 86, 95, 91, 98, 91, 92, 80, 83, 82]
//    }]
//  },
//  options: {
//    maintainAspectRatio: false,
//    legend: {
//      display: false
//    },
//    scales: {
//      xAxes: [{
//        gridLines: {
//          drawOnChartArea: false
//        }
//      }],
//      yAxes: [{
//        ticks: {
//          beginAtZero: true,
//          maxTicksLimit: 5,
//          stepSize: Math.ceil(10000 / 5),
//          max: 10000
//        }
//      }]
//    },
//    elements: {
//      point: {
//        radius: 0,
//        hitRadius: 10,
//        hoverRadius: 4,
//        hoverBorderWidth: 3
//      }
//    }
//  }
//});

var brandBoxChartLabels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
var brandBoxChartOptions = {
	responsive: true,
	maintainAspectRatio: false,
	legend: {
		display: false
	},
	scales: {
		xAxes: [{
			display: false
		}],
		yAxes: [{
			display: false
		}]
	},
	elements: {
		point: {
			radius: 0,
			hitRadius: 10,
			hoverRadius: 4,
			hoverBorderWidth: 3
		}
	} // eslint-disable-next-line no-unused-vars

};
var brandBoxChart1 = new Chart($('#social-box-chart-1'), {
	type: 'line',
	data: {
		labels: brandBoxChartLabels,
		datasets: [{
			label: 'My First dataset',
			backgroundColor: 'rgba(255,255,255,.1)',
			borderColor: 'rgba(255,255,255,.55)',
			pointHoverBackgroundColor: '#fff',
			borderWidth: 2,
			data: [65, 59, 84, 84, 51, 55, 40]
		}]
	},
	options: brandBoxChartOptions
}); // eslint-disable-next-line no-unused-vars

var brandBoxChart2 = new Chart($('#social-box-chart-2'), {
	type: 'line',
	data: {
		labels: brandBoxChartLabels,
		datasets: [{
			label: 'My First dataset',
			backgroundColor: 'rgba(255,255,255,.1)',
			borderColor: 'rgba(255,255,255,.55)',
			pointHoverBackgroundColor: '#fff',
			borderWidth: 2,
			data: [1, 13, 9, 17, 34, 41, 38]
		}]
	},
	options: brandBoxChartOptions
}); // eslint-disable-next-line no-unused-vars

var brandBoxChart3 = new Chart($('#social-box-chart-3'), {
	type: 'line',
	data: {
		labels: brandBoxChartLabels,
		datasets: [{
			label: 'My First dataset',
			backgroundColor: 'rgba(255,255,255,.1)',
			borderColor: 'rgba(255,255,255,.55)',
			pointHoverBackgroundColor: '#fff',
			borderWidth: 2,
			data: [78, 81, 80, 45, 34, 12, 40]
		}]
	},
	options: brandBoxChartOptions
}); // eslint-disable-next-line no-unused-vars

var brandBoxChart4 = new Chart($('#social-box-chart-4'), {
	type: 'line',
	data: {
		labels: brandBoxChartLabels,
		datasets: [{
			label: 'My First dataset',
			backgroundColor: 'rgba(255,255,255,.1)',
			borderColor: 'rgba(255,255,255,.55)',
			pointHoverBackgroundColor: '#fff',
			borderWidth: 2,
			data: [35, 23, 56, 22, 97, 23, 64]
		}]
	},
	options: brandBoxChartOptions
});
//# sourceMappingURL=main.js.map

// Sensors Chart

// Fetch the sensor data from the API and store it in a variable called jsonData.
fetch('http://localhost:8090/api/sensors-data/get-data/1')
	.then(response => response.json())
	.then(jsonData => {
		// Get the fetch URL from the JSON response
		var fetchUrl = jsonData.fetchUrl;

		// Convert the JSON data to a format that can be used by ChartJS.
		var sensorChartData = {
			labels: [],
			datasets: [{
				label: 'Sensor Readings',
				backgroundColor: hexToRgba(getStyle('--info'), 10),
				borderColor: getStyle('--info'),
				pointHoverBackgroundColor: '#fff',
				borderWidth: 2,
				data: []
			}]
		};

		jsonData.forEach(function(item) {
			sensorChartData.labels.push(moment(item.timeStamp).format('ddd'));
			sensorChartData.datasets[0].data.push(item.data);
		});

		// Create the ChartJS instance using the converted data and the fetch URL.
		var sensorChart = new Chart($('#sensors-chart'), {
			type: 'line',
			data: sensorChartData,
			options: {
				maintainAspectRatio: false,
				legend: {
					display: false
				},
				scales: {
					xAxes: [{
						gridLines: {
							drawOnChartArea: false
						}
					}],
					yAxes: [{
						ticks: {
							beginAtZero: true,
							maxTicksLimit: 5,
							stepSize: Math.ceil(20 / 5),
							max: 20
						}
					}]
				},
				elements: {
					point: {
						radius: 0,
						hitRadius: 10,
						hoverRadius: 4,
						hoverBorderWidth: 3
					}
				}
			}
		});
	});



// Fetch the sensor data from the API and store it in a variable called jsonData.
fetch('http://localhost:8090/api/sensors-data/get-data/1')
	.then(response => response.json())
	.then(jsonData => {
		// Get the fetch URL from the JSON response
		var fetchUrl = jsonData.fetchUrl;

		// Convert the JSON data to a format that can be used by ChartJS.
		var sensorChartData = {
			labels: [],
			datasets: [{
				label: 'Sensor Readings',
				backgroundColor: hexToRgba(getStyle('--info'), 10),
				borderColor: getStyle('--info'),
				pointHoverBackgroundColor: '#fff',
				borderWidth: 2,
				data: []
			}]
		};

		jsonData.forEach(function(item) {
			sensorChartData.labels.push(moment(item.timeStamp).format('ddd'));
			sensorChartData.datasets[0].data.push(item.data);
		});

		// Create the ChartJS instance using the converted data and the fetch URL.
		var dinagraphChart = new Chart($('#dinagraph-chart'), {
			type: 'line',
			data: sensorChartData,
			options: {
				maintainAspectRatio: false,
				legend: {
					display: false
				},
				scales: {
					xAxes: [{
						gridLines: {
							drawOnChartArea: false
						}
					}],
					yAxes: [{
						ticks: {
							beginAtZero: true,
							maxTicksLimit: 5,
							stepSize: Math.ceil(20 / 5),
							max: 20
						}
					}]
				},
				elements: {
					point: {
						radius: 0,
						hitRadius: 10,
						hoverRadius: 4,
						hoverBorderWidth: 3
					}
				}
			}
		});
	});


//OJO solo pruebas

const data = [];

for (let i = 0; i < 24; i++) {
	const value = Math.floor(Math.random() * (90000 - 10000 + 1) + 10000);
	const timestamp = new Date();
	timestamp.setHours(i);
	const formattedTimestamp = timestamp.toLocaleTimeString('es-CO', { hour12: false, hourCycle: 'h23' });
	if (timestamp > new Date()) {
		data.push({ value: 0, timestamp: formattedTimestamp });
	} else {
		data.push({ value, timestamp: formattedTimestamp });
	}
}
const chartData = {
	labels: data.map(item => item.timestamp),
	datasets: [{
		label: 'orinoco_2',
		data: data.map(item => item.value),
		borderColor: 'blue',
		fill: false
	}, {
		label: 'ecopetrol_2',
		data: data.map(item => item.value * 2),
		borderColor: 'green',
		fill: false
	}, {
		label: 'maracaibo',
		data: data.map(item => item.value * 3),
		borderColor: 'red',
		fill: false
	}, {
		label: 'puerto_gaitan_2',
		data: data.map(item => item.value * 4),
		borderColor: 'orange',
		fill: false
	}, {
		label: 'maracaibo_2',
		data: data.map(item => item.value * 5),
		borderColor: 'purple',
		fill: false
	}]
};

const chartOptions = {
	responsive: true,
	title: {
		display: false,
		text: 'Chart Title'
	}
};

const mainChart = new Chart($('#general-production-chart'), {
	type: 'line',
	data: chartData,
	options: chartOptions
});
