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
		
switch (chartSelection) {
	case "main":
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
				label: 'orinoco 2',
				data: data.map(item => item.value),
				borderColor: 'blue',
				fill: false
			}, {
				label: 'ecopetrol 2',
				data: data.map(item => item.value * 2),
				borderColor: 'green',
				fill: false
			}, {
				label: 'maracaibo',
				data: data.map(item => item.value * 3),
				borderColor: 'red',
				fill: false
			}, {
				label: 'puerto gaitan 2',
				data: data.map(item => item.value * 4),
				borderColor: 'orange',
				fill: false
			}, {
				label: 'maracaibo 2',
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
					backgroundColor: 'rgba(255,255,255,0)',
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
							max: 100
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

		break;
	case "sensorData":
		// Fetch the sensor data from the API and store it in a variable called jsonData.
		var fetchUrl = 'http://localhost:8080/api/sensors-data/get-today-data/';
		switch (productionChartRange) {
			case "year":
				fetchUrl = 'http://localhost:8080/api/sensors-data/get-year-data/';
				break;
			case "month":
				fetchUrl = 'http://localhost:8080/api/sensors-data/get-month-data/';
				break;
			case "week":
				fetchUrl = 'http://localhost:8080/api/sensors-data/get-week-data/';
				break;
			default:
				fetchUrl = fetchUrl;
		}
		fetch(fetchUrl.concat(sensorId))
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
				
				const rawData = sensorChartData.datasets[0].data
				const chartSize = findMaxAndMin(rawData);
				const maxValue = chartSize.max + 1;
				const minValue = chartSize.min - 1;
				
				// Create the ChartJS instance using the converted data and the fetch URL.
				var sensorsChart = new Chart($('#sensors-chart'), {
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
									stepSize: Math.ceil(maxValue / 5),
									max: maxValue
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
		break;
	case "dinagraphReading":
		// Fetch the sensor data from the API and store it in a variable called jsonData.
		fetch('http://localhost:8080/api/samples/get-data/'.concat(sampleId))
			.then(response => response.json())
			.then(jsonData => {
				// Get the fetch URL from the JSON response
				var rawData = JSON.parse(jsonData.data);

				const parsedData = rawData.map(obj => {
					const newObj = {};
					for (let key in obj) {
						newObj[key.replace(/"/g, '')] = obj[key];
					}
					return newObj;
				});

				const chartSize = getMinMaxY(parsedData);
				const maxValue = chartSize.max + 1;
				const minValue = chartSize.min - 1;

				// Convert the JSON data to a format that can be used by ChartJS.
				var dinagraphReadingData = {
					datasets: [{
						label: 'Oil well Dinagraph Readings',
						backgroundColor: hexToRgba(getStyle('--info'), 10),
						borderColor: getStyle('--info'),
						pointHoverBackgroundColor: '#fff',
						borderWidth: 2,
						showLine: true,
						tension: 0.1,
						data: parsedData
					}]
				};

				// Create the ChartJS instance using the converted data and the fetch URL.
				var dinagraphReadingChart = new Chart($('#dinagraph_chart'), {
					type: 'scatter',
					data: dinagraphReadingData,
					options: {
						maintainAspectRatio: false,
						legend: {
							display: false
						},
						scales: {
							xAxes: [{
								gridLines: {
									drawOnChartArea: false
								},
								scaleLabel: {
									display: true,
									labelString: 'Displacement (in)'
								}
							}],
							yAxes: [{
								ticks: {
									beginAtZero: true,
									maxTicksLimit: 5,
									stepSize: Math.ceil(maxValue / 5),
									max: maxValue
								},
								scaleLabel: {
									display: true,
									labelString: 'Load (klb)'
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
		break;
	case "dinagraphSample":
		// Fetch the dinagraph sample from the API and store it in a variable called jsonData.
		fetch('http://localhost:8080/api/samples/get-data/'.concat(sampleId))
			.then(response => response.json())
			.then(jsonData => {
				// Get the fetch URL from the JSON response
				var rawData = JSON.parse(jsonData.data);

				const parsedData = rawData.map(obj => {
					const newObj = {};
					for (let key in obj) {
						newObj[key.replace(/"/g, '')] = obj[key];
					}
					return newObj;
				});

				const chartSize = getMinMaxY(parsedData);
				const maxValue = chartSize.max + 1;
				const minValue = chartSize.min - 1;

				// Convert the JSON data to a format that can be used by ChartJS.
				var dinagraphSampleData = {
					datasets: [{
						label: 'Artix AI Readings',
						backgroundColor: hexToRgba(getStyle('--info'), 10),
						borderColor: getStyle('--info'),
						pointHoverBackgroundColor: '#fff',
						borderWidth: 2,
						showLine: true,
						tension: 0.1,
						data: parsedData
					}]
				};

				// Create the ChartJS instance using the converted data and the fetch URL.
				var dinagraphSampleChart = new Chart($('#dinagraph_sample_chart'), {
					type: 'scatter',
					data: dinagraphSampleData,
					options: {
						maintainAspectRatio: false,
						legend: {
							display: false
						},
						scales: {
							xAxes: [{
								gridLines: {
									drawOnChartArea: false
								},
								scaleLabel: {
									display: true,
									labelString: 'Displacement (in)'
								}
							}],
							yAxes: [{
								ticks: {
									beginAtZero: true,
									maxTicksLimit: 5,
									stepSize: Math.ceil(maxValue / 5),
									max: maxValue
								},
								scaleLabel: {
									display: true,
									labelString: 'Load (klb)'
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
		break;
	case "unitProduction":
		// Fetch the sensor data from the API and store it in a variable called jsonData.
		var fetchUrl = 'http://localhost:8080/api/unit-production/get-today-production-data/';
		switch (productionChartRange) {
			case "year":
				fetchUrl = 'http://localhost:8080/api/unit-production/get-year-production-data/';
				break;
			case "month":
				fetchUrl = 'http://localhost:8080/api/unit-production/get-month-production-data/';
				break;
			case "week":
				fetchUrl = 'http://localhost:8080/api/unit-production/get-week-production-data/';
				break;
			default:
				fetchUrl = fetchUrl;
		}
		fetch(fetchUrl.concat(unitId))
			.then(response => response.json())
			.then(jsonData => {
				// Get the fetch URL from the JSON response
				var fetchUrl = jsonData.fetchUrl;

				// Convert the JSON data to a format that can be used by ChartJS.
				var unitProductionChartData = {
					labels: [],
					datasets: [{
						label: 'Unit Production (BPD)',
						backgroundColor: hexToRgba(getStyle('--info'), 10),
						borderColor: getStyle('--info'),
						pointHoverBackgroundColor: '#fff',
						borderWidth: 2,
						data: []
					}]
				};

				jsonData.forEach(function(item) {
					unitProductionChartData.labels.push(moment(item.timeStamp).format('ddd'));
					unitProductionChartData.datasets[0].data.push(item.production);
				});
				
				const rawData = unitProductionChartData.datasets[0].data
				const chartSize = findMaxAndMin(rawData);
				const maxValue = chartSize.max + 1;
				const minValue = chartSize.min - 1;

				// Create the ChartJS instance using the converted data and the fetch URL.
				var sensorsChart = new Chart($('#unit-production-chart'), {
					type: 'line',
					data: unitProductionChartData,
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
									stepSize: Math.ceil(maxValue / 5),
									max: maxValue
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
		break;
	default:
		console.log("no charts in this view");
}

//function only for dinagraph samples dataset
function getMinMaxY(array) {
	let minY = Infinity;
	let maxY = -Infinity;

	for (let i = 0; i < array.length; i++) {
		const punto = array[i];
		const y = punto.y;

		if (y < minY) {
			minY = y;
		}

		if (y > maxY) {
			maxY = y;
		}
	}

	return { min: minY, max: maxY };
}

function findMaxAndMin(array) {
  let numbers = array.filter((value) => !isNaN(value));
  
  if (numbers.length === 0) {
    console.log("No valid numbers found in the array.");
    return;
  }
  
  const max = Math.max(...numbers);
  const min = Math.min(...numbers);
  
  return { min: min, max: max };
}
