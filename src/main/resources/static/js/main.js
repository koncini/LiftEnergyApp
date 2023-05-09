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
}); 

// eslint-disable-next-line no-unused-vars

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
	
fetch('http://localhost:8090/api/sensors-data/get-data/1')
.then(response => response.json())
.then(jsonData => {
	// Get the fetch URL from the JSON response
	var fetchUrl = jsonData.fetchUrl;

	// Convert the JSON data to a format that can be used by ChartJS.
	var dinagraphSampleData = {
		labels: [],
		datasets: [{
			label: 'Artix AI Readings',
			backgroundColor: hexToRgba(getStyle('--info'), 10),
			borderColor: getStyle('--info'),
			pointHoverBackgroundColor: '#fff',
			borderWidth: 2,
			data: []
		}]
	};
	
	jsonData = [{"load":0,"position":0.78},{"load":0.0395309820368803,"position":0.845884970061467},{"load":0.0790619640737608,"position":0.911769940122934},{"load":0.118592946110641,"position":0.977654910184402},{"load":0.158123928147521,"position":1.04353988024586},{"load":0.197654910184401,"position":1.10942485030733},{"load":0.237185892221282,"position":1.1753098203688},{"load":0.280121874527867,"position":1.23902817531292},{"load":0.337277632317551,"position":1.29037732126905},{"load":0.394461034782472,"position":1.34169580042882},{"load":0.451644457644784,"position":1.3930142568607},{"load":0.508827880515536,"position":1.44433271328317},{"load":0.56601130338629,"position":1.49565116970564},{"load":0.623194726257045,"position":1.54696962612811},{"load":0.672464398104403,"position":1.60592740401614},{"load":0.705996031898957,"position":1.67505868190261},{"load":0.73946218702201,"position":1.74422185332874},{"load":0.772928257584204,"position":1.81338506567408},{"load":0.806394328087668,"position":1.88254827804784},{"load":0.839860398591106,"position":1.95171149042162},{"load":0.873326469094544,"position":2.02087470279539},{"load":0.906792539597982,"position":2.09003791516916},{"load":0.940274404972725,"position":2.15919348263699},{"load":0.976085306341076,"position":2.22717220318976},{"load":1.01515176878242,"position":2.29333365573841},{"load":1.05424910462761,"position":2.35947688026266},{"load":1.09528567441456,"position":2.4244347677103},{"load":1.1528965773977,"position":2.47527227857041},{"load":1.21061166192592,"position":2.52599206598861},{"load":1.26832697960016,"position":2.57671158813308},{"load":1.32604229754789,"position":2.62743110996632},{"load":1.38380481331702,"position":2.67809687347692},{"load":1.45121334683488,"position":2.71496931219821},{"load":1.52700364375125,"position":2.72758928199444},{"load":1.60282047254267,"position":2.7400526799843},{"load":1.67863735853051,"position":2.75251573016907},{"load":1.75445424458765,"position":2.76497877993221},{"load":1.83027113064485,"position":2.77744182969504},{"load":1.90608801670204,"position":2.78990487945787},{"load":1.98190490275924,"position":2.80236792922069},{"load":2.05772178881644,"position":2.81483097898352},{"load":2.13357405321989,"position":2.82707686797621},{"load":2.21019141277045,"position":2.8328475182812},{"load":2.28693796997328,"position":2.83652079584186},{"load":2.36368541935806,"position":2.84017549605796},{"load":2.44043287157814,"position":2.8438301367417},{"load":2.51718110724919,"position":2.84746828766306},{"load":2.59395409280519,"position":2.85054016725311},{"load":2.67076766294819,"position":2.85232983748052},{"load":2.74758155043005,"position":2.85410593170294},{"load":2.82439543907877,"position":2.85588197546991},{"load":2.90120932772977,"position":2.85765801913826},{"load":2.97802321638078,"position":2.85943406280649},{"load":3.05483710503178,"position":2.86121010647472},{"load":3.13165099368279,"position":2.86298615014295},{"load":3.2084648823338,"position":2.86476219381118},{"load":3.2852787709848,"position":2.86653823747941},{"load":3.36209265963581,"position":2.86831428114764},{"load":3.43890654828681,"position":2.87009032481588},{"load":3.51572043693782,"position":2.87186636848411},{"load":3.59253432558883,"position":2.87364241215234},{"load":3.66934821423983,"position":2.87541845582057},{"load":3.74616210289084,"position":2.8771944994888},{"load":3.82297599154184,"position":2.87897054315703},{"load":3.89978988019285,"position":2.88074658682526},{"load":3.97660376884386,"position":2.88252263049349},{"load":4.05341765749486,"position":2.88429867416173},{"load":4.13023154614587,"position":2.88607471782996},{"load":4.20704543479687,"position":2.88785076149819},{"load":4.2838587489686,"position":2.88965147984502},{"load":4.36060050932292,"position":2.89342358799484},{"load":4.43731380959455,"position":2.89773604528032},{"load":4.51402658523264,"position":2.90205783595932},{"load":4.59073935774482,"position":2.90637968212678},{"load":4.6674521302474,"position":2.91070152846464},{"load":4.74416490274996,"position":2.91502337480281},{"load":4.82087767525252,"position":2.91934522114098},{"load":4.89759044775508,"position":2.92366706747916},{"load":4.97430342688563,"position":2.92798524460759},{"load":5.05105133555357,"position":2.93163026462597},{"load":5.12782389065708,"position":2.93471287506902},{"load":5.20459691178349,"position":2.93778387618854},{"load":5.28136993610483,"position":2.9408547974436},{"load":5.35814296043743,"position":2.94392571841749},{"load":5.43491598477004,"position":2.9469966393908},{"load":5.51168900910266,"position":2.9500675603641},{"load":5.58846203343527,"position":2.95313848133741},{"load":5.66523505776789,"position":2.95620940231071},{"load":5.7420080821005,"position":2.95928032328401},{"load":5.81883927988908,"position":2.95998214095741},{"load":5.89567369478852,"position":2.9599998304919},{"load":5.97250811299187,"position":2.95999999914118},{"load":6.04934253119622,"position":2.95999999999734},{"load":6.12617694940056,"position":2.95999999999999},{"load":6.20301136760491,"position":2.96},{"load":6.27984578580926,"position":2.95999999999999},{"load":6.35668020401361,"position":2.96},{"load":6.43351462221796,"position":2.95999999999999},{"load":6.5103490404223,"position":2.95999999999999},{"load":6.58718345862665,"position":2.95999999999999},{"load":6.664017876831,"position":2.96},{"load":6.74085229503535,"position":2.96},{"load":6.81768671323969,"position":2.95999999999999},{"load":6.89452113144404,"position":2.95999999999999},{"load":6.97135554964839,"position":2.95999999999999},{"load":7.04818996785274,"position":2.96},{"load":7.12502438605709,"position":2.96},{"load":7.20185880426143,"position":2.95999999999999},{"load":7.27869322246578,"position":2.96},{"load":7.35552764067013,"position":2.95999999999999},{"load":7.43236205887448,"position":2.95999999999999},{"load":7.50919647707882,"position":2.96},{"load":7.58603089528317,"position":2.95999999999999},{"load":7.66286531348752,"position":2.96},{"load":7.73969973169187,"position":2.96},{"load":7.81653414989622,"position":2.96},{"load":7.89336856810056,"position":2.96},{"load":7.97020298630491,"position":2.96},{"load":8.04703740450926,"position":2.96},{"load":8.1238718227136,"position":2.95999999999999},{"load":8.20070624091796,"position":2.96},{"load":8.2775406591223,"position":2.95999999999999},{"load":8.35437507732665,"position":2.95999999999999},{"load":8.431209495531,"position":2.96},{"load":8.50804391373535,"position":2.96},{"load":8.58487833193969,"position":2.96},{"load":8.66171275014404,"position":2.96},{"load":8.73854716834839,"position":2.96},{"load":8.81537682324802,"position":2.95914471964139},{"load":8.89217981340742,"position":2.95694758030673},{"load":8.96898008928007,"position":2.95465728743915},{"load":9.04578029872745,"position":2.9523647670719},{"load":9.12258050748687,"position":2.95007222365666},{"load":9.19938071624243,"position":2.94777968011216},{"load":9.27618092499797,"position":2.94548713656722},{"load":9.35298113375352,"position":2.94319459302228},{"load":9.42978134250906,"position":2.94090204947734},{"load":9.5065873834678,"position":2.93881402032402},{"load":9.58339634173652,"position":2.93683620776816},{"load":9.66020550903839,"position":2.93486652780681},{"load":9.73701468176677,"position":2.93289705945629},{"load":9.81382385455584,"position":2.93092759347297},{"load":9.89063302734527,"position":2.92895812750396},{"load":9.96744220013471,"position":2.926988661535},{"load":10.0442513729241,"position":2.92501919556604},{"load":10.1210605457135,"position":2.92304972959708},{"load":10.197869718503,"position":2.92108026362812},{"load":10.2746788912924,"position":2.91911079765916},{"load":10.3514880640819,"position":2.9171413316902},{"load":10.4282972368713,"position":2.91517186572124},{"load":10.5051064096607,"position":2.91320239975228},{"load":10.5819155824502,"position":2.91123293378332},{"load":10.6587295654917,"position":2.90946098521823},{"load":10.7355487515753,"position":2.90793113967987},{"load":10.8123683898695,"position":2.90642415833403},{"load":10.8891880419852,"position":2.90491788159136},{"load":10.9660076942771,"position":2.90341161383795},{"load":11.0428273465702,"position":2.90190534614568},{"load":11.1196469988634,"position":2.90039907845365},{"load":11.1964666511566,"position":2.89889281076163},{"load":11.2732863034497,"position":2.89738654306961},{"load":11.3501059557429,"position":2.89588027537759},{"load":11.426925608036,"position":2.89437400768556},{"load":11.5037452603292,"position":2.89286773999354},{"load":11.5805649126223,"position":2.89136147230152},{"load":11.65736860371,"position":2.88918880016996},{"load":11.7340527178473,"position":2.88438578686254},{"load":11.8107176589952,"position":2.87928534086218},{"load":11.8873819108772,"position":2.87417453817606},{"load":11.9640461530466,"position":2.869063589792},{"load":12.0407103951438,"position":2.86395264032373},{"load":12.1173746372406,"position":2.85884169085062},{"load":12.1940388793375,"position":2.85373074137749},{"load":12.2707031214343,"position":2.84861979190437},{"load":12.3473673635312,"position":2.84350884243125},{"load":12.424031605628,"position":2.83839789295812},{"load":12.5006958477249,"position":2.833286943485},{"load":12.5774833308628,"position":2.83060268790671},{"load":12.6543172642348,"position":2.83086172146353},{"load":12.7311492504236,"position":2.83147296111772},{"load":12.8079811310805,"position":2.83209740918116},{"load":12.8848130101145,"position":2.83272205700049},{"load":12.9616448891356,"position":2.83334670641576},{"load":13.0384767681566,"position":2.83397135583867},{"load":13.1153086471776,"position":2.8345960052616},{"load":13.1921405261986,"position":2.83522065468454},{"load":13.2689724052196,"position":2.83584530410747},{"load":13.3458042842406,"position":2.83646995353041},{"load":13.4226361632616,"position":2.83709460295334},{"load":13.4994680422826,"position":2.83771925237628},{"load":13.5762999213037,"position":2.83834390179921},{"load":13.6531318003247,"position":2.83896855122215},{"load":13.7299636793457,"position":2.83959320064508},{"load":13.8067955583667,"position":2.84021785006802},{"load":13.8836274373877,"position":2.84084249949095},{"load":13.9604593164087,"position":2.84146714891389},{"load":14.0372911954297,"position":2.84209179833682},{"load":14.1141230744507,"position":2.84271644775976},{"load":14.1909549534717,"position":2.84334109718269},{"load":14.2677868324927,"position":2.84396574660563},{"load":14.3446187115138,"position":2.84459039602856},{"load":14.4214505905348,"position":2.8452150454515},{"load":14.4982824695558,"position":2.84583969487443},{"load":14.5751143485768,"position":2.84646434429737},{"load":14.6519462275978,"position":2.8470889937203},{"load":14.7287781066188,"position":2.84771364314324},{"load":14.8056099856398,"position":2.84833829256617},{"load":14.8824418646608,"position":2.84896294198911},{"load":14.9592737436818,"position":2.84958759141204},{"load":15.0361056227028,"position":2.85021224083498},{"load":15.1129375017239,"position":2.85083689025791},{"load":15.1897693807449,"position":2.85146153968085},{"load":15.2666012597659,"position":2.85208618910378},{"load":15.3434331387869,"position":2.85271083852672},{"load":15.4202650178079,"position":2.85333548794965},{"load":15.4970968968289,"position":2.85396013737259},{"load":15.5739287758499,"position":2.85458478679552},{"load":15.6507606548709,"position":2.85520943621846},{"load":15.7275925338919,"position":2.85583408564139},{"load":15.8044244129129,"position":2.85645873506433},{"load":15.881256291934,"position":2.85708338448726},{"load":15.958088170955,"position":2.8577080339102},{"load":16.034920049976,"position":2.85833268333313},{"load":16.111751928997,"position":2.85895733275607},{"load":16.188583808018,"position":2.859581982179},{"load":16.2654135800546,"position":2.8604269062662},{"load":16.3422401042748,"position":2.86152823509789},{"load":16.4190659989062,"position":2.86267267070612},{"load":16.4958918611105,"position":2.86381928195781},{"load":16.5727177226738,"position":2.86496593616205},{"load":16.6495435842304,"position":2.86611259080942},{"load":16.726369445787,"position":2.8672592454595},{"load":16.8031953073436,"position":2.8684059001096},{"load":16.8800211689002,"position":2.8695525547597},{"load":16.9568470304568,"position":2.8706992094098},{"load":17.0336728920134,"position":2.8718458640599},{"load":17.11049875357,"position":2.87299251871},{"load":17.1873246151266,"position":2.8741391733601},{"load":17.2641504766832,"position":2.87528582801019},{"load":17.3409763382398,"position":2.87643248266029},{"load":17.4178021997964,"position":2.87757913731039},{"load":17.494628061353,"position":2.87872579196049},{"load":17.5714539229096,"position":2.87987244661059},{"load":17.6482797844662,"position":2.88101910126069},{"load":17.7251056460228,"position":2.88216575591079},{"load":17.8019315075794,"position":2.88331241056088},{"load":17.878757369136,"position":2.88445906521098},{"load":17.9555832306926,"position":2.88560571986108},{"load":18.0324090922492,"position":2.88675237451118},{"load":18.1092349538059,"position":2.88789902916128},{"load":18.1860608153625,"position":2.88904568381137},{"load":18.2628866769191,"position":2.89019233846147},{"load":18.3397125384757,"position":2.89133899311157},{"load":18.4165384000323,"position":2.89248564776167},{"load":18.4933642615889,"position":2.89363230241177},{"load":18.5701901231455,"position":2.89477895706187},{"load":18.6470159847021,"position":2.89592561171197},{"load":18.7238418462587,"position":2.89707226636207},{"load":18.8006677078153,"position":2.89821892101216},{"load":18.8774935693719,"position":2.89936557566226},{"load":18.9543272808249,"position":2.89969384074802},{"load":19.031161164741,"position":2.89940813781314},{"load":19.1079945533934,"position":2.89901040271653},{"load":19.1848279082226,"position":2.89860616710096},{"load":19.2616612622699,"position":2.89820178281738},{"load":19.3384946163078,"position":2.89779739675621},{"load":19.4153279703457,"position":2.89739301068238},{"load":19.4921613243835,"position":2.8969886246085},{"load":19.5689946784213,"position":2.89658423853462},{"load":19.6458280324592,"position":2.89617985246074},{"load":19.722661386497,"position":2.89577546638685},{"load":19.7994947405349,"position":2.89537108031297},{"load":19.8763280945727,"position":2.89496669423909},{"load":19.9531614486105,"position":2.8945623081652},{"load":20.0299948026484,"position":2.89415792209132},{"load":20.1068281566862,"position":2.89375353601744},{"load":20.183661510724,"position":2.89334914994355},{"load":20.2604948647619,"position":2.89294476386967},{"load":20.3373282187997,"position":2.89254037779579},{"load":20.4141615728376,"position":2.8921359917219},{"load":20.4909949268754,"position":2.89173160564802},{"load":20.5678282809132,"position":2.89132721957414},{"load":20.6446616349511,"position":2.89092283350025},{"load":20.7214949889889,"position":2.89051844742637},{"load":20.7983283430268,"position":2.89011406135248},{"load":20.8751595096562,"position":2.8894072883429},{"load":20.9519897424806,"position":2.88860533296328},{"load":21.0288198784898,"position":2.88779414772464},{"load":21.105650010357,"position":2.88698257021205},{"load":21.1824801421368,"position":2.8861709844133},{"load":21.2593102739155,"position":2.88535939851497},{"load":21.3361404056941,"position":2.8845478126159},{"load":21.4129705374728,"position":2.88373622671683},{"load":21.4898006692515,"position":2.88292464081776},{"load":21.5666308010302,"position":2.88211305491869},{"load":21.6434609328088,"position":2.88130146901962},{"load":21.7202910645875,"position":2.88048988312055},{"load":21.7971211963662,"position":2.87967829722148},{"load":21.8739513281449,"position":2.87886671132241},{"load":21.9507814599236,"position":2.87805512542334},{"load":22.0276115917022,"position":2.87724353952427},{"load":22.1044417234809,"position":2.8764319536252},{"load":22.1812718552596,"position":2.87562036772612},{"load":22.2581019870383,"position":2.87480878182705},{"load":22.334932118817,"position":2.87399719592799},{"load":22.4117622505956,"position":2.87318561002891},{"load":22.4885923823743,"position":2.87237402412984},{"load":22.565422514153,"position":2.87156243823077},{"load":22.6422526459317,"position":2.8707508523317},{"load":22.7190827777104,"position":2.86993926643263},{"load":22.795912909489,"position":2.86912768053356},{"load":22.8727430412677,"position":2.86831609463449},{"load":22.9495731730464,"position":2.86750450873542},{"load":23.0264033048251,"position":2.86669292283635},{"load":23.1032334366038,"position":2.86588133693728},{"load":23.1800635683824,"position":2.86506975103821},{"load":23.2568937001611,"position":2.86425816513914},{"load":23.3337238319398,"position":2.86344657924007},{"load":23.4105539637185,"position":2.862634993341},{"load":23.4873840954971,"position":2.86182340744193},{"load":23.5642142272758,"position":2.86101182154286},{"load":23.6410444761329,"position":2.86021139592727},{"load":23.717877334857,"position":2.85972202006615},{"load":23.7947107699441,"position":2.85933335870658},{"load":23.8715442454703,"position":2.85895275488396},{"load":23.9483777221682,"position":2.85857238754604},{"load":24.0252111988837,"position":2.85819202376808},{"load":24.1020446755994,"position":2.85781166002178},{"load":24.1788781523151,"position":2.85743129627566},{"load":24.2557116290308,"position":2.85705093252954},{"load":24.3325451057465,"position":2.85667056878343},{"load":24.4093785824622,"position":2.85629020503731},{"load":24.4862120591779,"position":2.85590984129119},{"load":24.5630455358936,"position":2.85552947754508},{"load":24.6398790126093,"position":2.85514911379896},{"load":24.716712489325,"position":2.85476875005284},{"load":24.7935459660407,"position":2.85438838630673},{"load":24.8703794427564,"position":2.85400802256061},{"load":24.9472129194721,"position":2.85362765881449},{"load":25.0240463961878,"position":2.85324729506837},{"load":25.1008798729034,"position":2.85286693132226},{"load":25.1777133496191,"position":2.85248656757614},{"load":25.2545468263348,"position":2.85210620383002},{"load":25.3313803030505,"position":2.8517258400839},{"load":25.4082137797662,"position":2.85134547633779},{"load":25.4850472564819,"position":2.85096511259167},{"load":25.5618807331976,"position":2.85058474884555},{"load":25.6387142099133,"position":2.85020438509943},{"load":25.7155473340309,"position":2.85064901846082},{"load":25.7923753854131,"position":2.85163787528843},{"load":25.8692018650791,"position":2.85274233008101},{"load":25.9460282193143,"position":2.85385548577596},{"load":26.0228545697047,"position":2.85496890684846},{"load":26.0996809200335,"position":2.85608233217464},{"load":26.1765072703617,"position":2.85719575754147},{"load":26.25333362069,"position":2.85830918290855},{"load":26.3301599710182,"position":2.85942260827562},{"load":26.4069863213464,"position":2.8605360336427},{"load":26.4838126716746,"position":2.86164945900977},{"load":26.5606390220028,"position":2.86276288437685},{"load":26.637465372331,"position":2.86387630974392},{"load":26.7142917226592,"position":2.864989735111},{"load":26.7911180729874,"position":2.86610316047807},{"load":26.8679444233156,"position":2.86721658584515},{"load":26.9447707736438,"position":2.86833001121223},{"load":27.0215971239721,"position":2.8694434365793},{"load":27.0984283807425,"position":2.87014020933347},{"load":27.1752614982025,"position":2.87058716631192},{"load":27.2520948865116,"position":2.87098498218152},{"load":27.3289282938169,"position":2.87137911747918},{"load":27.4057617017166,"position":2.87177313691224},{"load":27.4825951096263,"position":2.87216715440821},{"load":27.5594285175361,"position":2.8725611718848},{"load":27.6362619254458,"position":2.87295518936126},{"load":27.7130953333556,"position":2.87334920683772},{"load":27.7899287412654,"position":2.87374322431418},{"load":27.8667621491751,"position":2.87413724179064},{"load":27.9435955570849,"position":2.8745312592671},{"load":28.0204289649947,"position":2.87492527674356},{"load":28.0972623729044,"position":2.87531929422002},{"load":28.1740957808142,"position":2.87571331169648},{"load":28.250929188724,"position":2.87610732917294},{"load":28.3277625966338,"position":2.8765013466494},{"load":28.4045960045435,"position":2.87689536412586},{"load":28.4814294124533,"position":2.87728938160232},{"load":28.5582628203631,"position":2.87768339907878},{"load":28.6350962282728,"position":2.87807741655524},{"load":28.7119296361826,"position":2.8784714340317},{"load":28.7887630440924,"position":2.87886545150816},{"load":28.8655964520021,"position":2.87925946898462},{"load":28.9424298599119,"position":2.87965348646108},{"load":29.019264143609,"position":2.8797968354477},{"load":29.096098189435,"position":2.87955820879473},{"load":29.1729317200156,"position":2.87918893010796},{"load":29.249765189605,"position":2.87880712995502},{"load":29.3265986569709,"position":2.87842488221307},{"load":29.4034321242959,"position":2.87804262624666},{"load":29.4802655916204,"position":2.87766037019094},{"load":29.5570990589449,"position":2.8772781141346},{"load":29.6339325262695,"position":2.87689585807826},{"load":29.710765993594,"position":2.87651360202191},{"load":29.7875994609185,"position":2.87613134596557},{"load":29.864432928243,"position":2.87574908990923},{"load":29.9412663955675,"position":2.87536683385289},{"load":30.0180998628921,"position":2.87498457779655},{"load":30.0949333302166,"position":2.87460232174021},{"load":30.1717667975411,"position":2.87422006568387},{"load":30.2486002648656,"position":2.87383780962753},{"load":30.3254337321901,"position":2.87345555357119},{"load":30.4022671995147,"position":2.87307329751485},{"load":30.4791006668392,"position":2.87269104145851},{"load":30.5559341341637,"position":2.87230878540217},{"load":30.6327676014882,"position":2.87192652934583},{"load":30.7096010688127,"position":2.87154427328948},{"load":30.7864345361373,"position":2.87116201723314},{"load":30.8632680034618,"position":2.8707797611768},{"load":30.9401014707863,"position":2.87039750512046},{"load":31.0169335833251,"position":2.86980234909068},{"load":31.0937601607827,"position":2.8687049433117},{"load":31.1705835447821,"position":2.86740286564903},{"load":31.2474065600662,"position":2.86607918044369},{"load":31.3242295610044,"position":2.86475466254781},{"load":31.4010525616597,"position":2.86343012824583},{"load":31.4778755623117,"position":2.86210559375323},{"load":31.5546985629637,"position":2.86078105925924},{"load":31.6315326285452,"position":2.86055374724971},{"load":31.7083648346797,"position":2.86113560426865},{"load":31.7851953509325,"position":2.86190988893703},{"load":31.8620256930854,"position":2.86270130847086},{"load":31.9388560287164,"position":2.86349336126729},{"load":32.0156863642198,"position":2.86428542643633},{"load":32.0925166997218,"position":2.86507749174971},{"load":32.1693470352237,"position":2.86586955706416},{"load":32.2461773707256,"position":2.86666162237861},{"load":32.3230077062275,"position":2.86745368769306},{"load":32.3998380417294,"position":2.86824575300752},{"load":32.4766683772314,"position":2.86903781832197},{"load":32.5534989406968,"position":2.86980745285332},{"load":32.6303304787597,"position":2.87047270184779},{"load":32.7071624303888,"position":2.87108835142587},{"load":32.7839944270171,"position":2.87169836359157},{"load":32.8608264254683,"position":2.87230814617489},{"load":32.9376584239572,"position":2.87291792399921},{"load":33.0144904224466,"position":2.87352770176544},{"load":33.091322420936,"position":2.87413747953123},{"load":33.1681544194254,"position":2.87474725729702},{"load":33.2449864179148,"position":2.87535703506281},{"load":33.3218184164043,"position":2.8759668128286},{"load":33.3986504148937,"position":2.87657659059439},{"load":33.4754824133831,"position":2.87718636836018},{"load":33.5523144118725,"position":2.87779614612597},{"load":33.6291464103619,"position":2.87840592389176},{"load":33.7059784088513,"position":2.87901570165755},{"load":33.7828104073407,"position":2.87962547942334},{"load":33.8596071103497,"position":2.88203110156353},{"load":33.9363472315798,"position":2.8858359995256},{"load":34.013069439808,"position":2.88998694169097},{"load":34.0897898844307,"position":2.89417038470172},{"load":34.1665102593875,"position":2.89835510539515},{"load":34.2432306328925,"position":2.90253985270588},{"load":34.3199510063794,"position":2.90672460034798},{"load":34.3967355412989,"position":2.90949199106431},{"load":34.4735603430999,"position":2.91070532463619},{"load":34.5503916691971,"position":2.91139418802406},{"load":34.6272234865646,"position":2.91202636978108},{"load":34.7040553233683,"position":2.91265619051408},{"load":34.7808871605895,"position":2.91328596032739},{"load":34.857718997816,"position":2.91391572949026},{"load":34.9345508350426,"position":2.91454549864788},{"load":35.0113826722692,"position":2.91517526780548},{"load":35.0882145094957,"position":2.91580503696308},{"load":35.1650463467223,"position":2.91643480612067},{"load":35.2418781839488,"position":2.91706457527826},{"load":35.3187100211754,"position":2.91769434443586},{"load":35.395541858402,"position":2.91832411359346},{"load":35.4723736956285,"position":2.91895388275105},{"load":35.5492055328551,"position":2.91958365190864},{"load":35.6260271849651,"position":2.92098372142758},{"load":35.702833962734,"position":2.92304422829746},{"load":35.7796357225036,"position":2.92528419312478},{"load":35.8564369520651,"position":2.92754228034369},{"load":35.9332511166847,"position":2.92930618395364},{"load":36.0100830149117,"position":2.92992597088942},{"load":36.086917395825,"position":2.92999683181401},{"load":36.1637518138985,"position":2.92999992915667},{"load":36.2405862321027,"position":2.92999999905885},{"load":36.317420650307,"position":2.92999999999209},{"load":36.3942550685114,"position":2.92999999999995},{"load":36.4710894867157,"position":2.92999999999999},{"load":36.54792390492,"position":2.93},{"load":36.6247232793739,"position":2.92768246974858},{"load":36.7012142065878,"position":2.92042940602549},{"load":36.7774474130484,"position":2.91083707495638},{"load":36.8536436938103,"position":2.90095504983366},{"load":36.9298382802659,"position":2.89105995936993},{"load":37.0060328276205,"position":2.88116456780546},{"load":37.0822273744439,"position":2.87126917214993},{"load":37.1584219212628,"position":2.86137377645937},{"load":37.2346164680816,"position":2.85147838076861},{"load":37.3108110149004,"position":2.84158298507786},{"load":37.3802026404129,"position":2.80861465837589},{"load":37.3355701549219,"position":2.74636256188414},{"load":37.2607469018725,"position":2.72890897254416},{"load":37.1839257455043,"position":2.72748147837076},{"load":37.1071027448523,"position":2.72615694387676},{"load":37.0302797442003,"position":2.72483240938276},{"load":36.9534567435483,"position":2.72350787488876},{"load":36.8766337428963,"position":2.72218334039476},{"load":36.7998107422444,"position":2.72085880590076},{"load":36.7229877415924,"position":2.71953427140676},{"load":36.6461647409404,"position":2.71820973691276},{"load":36.5693417402884,"position":2.71688520241876},{"load":36.4925187396364,"position":2.71556066792476},{"load":36.4156957389844,"position":2.71423613343076},{"load":36.3388727383324,"position":2.71291159893676},{"load":36.2620497376805,"position":2.71158706444276},{"load":36.1852267370285,"position":2.71026252994876},{"load":36.1084037363765,"position":2.70893799545476},{"load":36.0315807357245,"position":2.70761346096077},{"load":35.9547577350725,"position":2.7062889264675},{"load":35.8779347344178,"position":2.70496439213179},{"load":35.8011117334009,"position":2.70363987880336},{"load":35.7242887049984,"position":2.70231695643215},{"load":35.6474647226837,"position":2.7010508641296},{"load":35.5706327964295,"position":2.70043768446344},{"load":35.4938161672146,"position":2.70209095288645},{"load":35.4169998981576,"position":2.7037608717396},{"load":35.3401836289841,"position":2.70543078522655},{"load":35.2633673507552,"position":2.70710028202845},{"load":35.1865507454746,"position":2.70875465151596},{"load":35.1097307772486,"position":2.71024458868905},{"load":35.0329021927298,"position":2.71119138413828},{"load":34.9560735576369,"position":2.71213406677746},{"load":34.879244922544,"position":2.71307674941663},{"load":34.802416287451,"position":2.71401943205581},{"load":34.7255876523581,"position":2.71496211469499},{"load":34.6487590172652,"position":2.71590479733416},{"load":34.5719303821722,"position":2.71684747997334},{"load":34.4951017470793,"position":2.71779016261252},{"load":34.4182731119864,"position":2.7187328452517},{"load":34.3414444768935,"position":2.71967552789087},{"load":34.2646158418005,"position":2.72061821053005},{"load":34.1877872067076,"position":2.72156089316923},{"load":34.1109585716147,"position":2.7225035758084},{"load":34.0341299365218,"position":2.72344625844758},{"load":33.9573013014288,"position":2.72438894108676},{"load":33.8804726663359,"position":2.72533162372593},{"load":33.803644031243,"position":2.72627430636494},{"load":33.7268153961495,"position":2.72721698896221},{"load":33.6499867609773,"position":2.72815966514197},{"load":33.5731581189614,"position":2.72910178308368},{"load":33.4963292125735,"position":2.73002207560166},{"load":33.4194987016147,"position":2.73079693453654},{"load":33.3426680502268,"position":2.73155774207696},{"load":33.2658373977694,"position":2.73231844160624},{"load":33.189006745312,"position":2.73307914113552},{"load":33.1121760928546,"position":2.7338398406648},{"load":33.0353454403973,"position":2.73460054019408},{"load":32.9585147879399,"position":2.73536123972336},{"load":32.8816841354825,"position":2.73612193925264},{"load":32.8048534830251,"position":2.73688263878157},{"load":32.7280228305668,"position":2.73764333821492},{"load":32.6511921779475,"position":2.73840402139418},{"load":32.5743615096846,"position":2.73916311911243},{"load":32.4975301747689,"position":2.73985120322644},{"load":32.4206958983739,"position":2.73999874010265},{"load":32.3438614801799,"position":2.74},{"load":32.2670270619755,"position":2.74},{"load":32.1901926437712,"position":2.74},{"load":32.1133582255668,"position":2.73999999999993},{"load":32.0365238073625,"position":2.73999999998154},{"load":31.9596893891581,"position":2.73999999654434},{"load":31.8828549709573,"position":2.73999962064166},{"load":31.8060205556797,"position":2.73997983244077},{"load":31.7291865475215,"position":2.73972993925095},{"load":31.6523582009563,"position":2.7387640278602},{"load":31.5755299376299,"position":2.73779151819784},{"load":31.4987016743034,"position":2.73681900853548},{"load":31.421873410977,"position":2.73584649887312},{"load":31.3450451476506,"position":2.73487398921076},{"load":31.2682168843241,"position":2.73390147954858},{"load":31.1913886209969,"position":2.7329289699444},{"load":31.1145603575153,"position":2.73195647254081},{"load":31.0377320751296,"position":2.73098547367827},{"load":30.9609027578488,"position":2.73010049980776},{"load":30.8840684042911,"position":2.73000088066979},{"load":30.8072339860918,"position":2.72999999999999},{"load":30.7303995678875,"position":2.73},{"load":30.6535651496831,"position":2.72999999999999},{"load":30.5767307314788,"position":2.73},{"load":30.4998963132745,"position":2.73},{"load":30.4230618950701,"position":2.73},{"load":30.3462274768658,"position":2.72999999999999},{"load":30.2693930586614,"position":2.73},{"load":30.1925586404571,"position":2.73},{"load":30.1157242222527,"position":2.73},{"load":30.0388898040484,"position":2.72999999999998},{"load":29.962055385844,"position":2.72999999999269},{"load":29.8852209676397,"position":2.72999999789301},{"load":29.8083865494401,"position":2.72999965431547},{"load":29.7315521361051,"position":2.72997390632965},{"load":29.6547183945537,"position":2.72965154936463},{"load":29.5778861936547,"position":2.72906783213941},{"load":29.5010540139764,"position":2.72848132835096},{"load":29.4242218343089,"position":2.72789482316266},{"load":29.3473896546414,"position":2.72730831797436},{"load":29.2705574749738,"position":2.72672181278605},{"load":29.1937252953063,"position":2.72613530759775},{"load":29.1168931156387,"position":2.72554880240945},{"load":29.0400609359712,"position":2.72496229722115},{"load":28.9632287563036,"position":2.72437579203285},{"load":28.8863965766361,"position":2.72378928684455},{"load":28.8095643969686,"position":2.72320278165624},{"load":28.732732217301,"position":2.72261627646795},{"load":28.6559000376335,"position":2.72202977128357},{"load":28.5790678579561,"position":2.72144326737654},{"load":28.5022356764872,"position":2.72085699851162},{"load":28.4254033474059,"position":2.72029046582719},{"load":28.3485694632133,"position":2.72000412601917},{"load":28.2717350451192,"position":2.72000001158601},{"load":28.1949006269149,"position":2.72},{"load":28.1180662087106,"position":2.71999999999999},{"load":28.0412317905062,"position":2.71999999999999},{"load":27.9643973723019,"position":2.72},{"load":27.8875629540975,"position":2.71999999999999},{"load":27.8107285358932,"position":2.72},{"load":27.7338941176888,"position":2.72},{"load":27.6570596994845,"position":2.72},{"load":27.5802252812801,"position":2.72},{"load":27.5033908630758,"position":2.72},{"load":27.4265564448714,"position":2.71999999999999},{"load":27.3497220266671,"position":2.71999999999999},{"load":27.2728876084627,"position":2.71999999999999},{"load":27.1960531902584,"position":2.71999999999999},{"load":27.119218772054,"position":2.72},{"load":27.0423843538497,"position":2.72},{"load":26.9655499356453,"position":2.71999999999999},{"load":26.888715517441,"position":2.71999999999999},{"load":26.8118810992366,"position":2.71999999999999},{"load":26.7350466810323,"position":2.72},{"load":26.6582122628279,"position":2.71999999999999},{"load":26.5813778446236,"position":2.72},{"load":26.5045434264193,"position":2.71999999999999},{"load":26.4277090082149,"position":2.71999999999999},{"load":26.3508745900105,"position":2.72},{"load":26.2740401718062,"position":2.71999999999999},{"load":26.1972057536019,"position":2.71999999999999},{"load":26.1203713353975,"position":2.71999999999999},{"load":26.0435369171932,"position":2.71999999999999},{"load":25.9667024989888,"position":2.71999999999999},{"load":25.8898680807845,"position":2.71999999999764},{"load":25.8130336625801,"position":2.71999999895283},{"load":25.7361992443801,"position":2.7199997401355},{"load":25.6593648344383,"position":2.71997001394365},{"load":25.5825342722491,"position":2.71920055201861},{"load":25.5057161335509,"position":2.71761897448609},{"load":25.4288983817328,"position":2.71601871625111},{"load":25.3520806322377,"position":2.71441834651316},{"load":25.2752628826989,"position":2.71281797886762},{"load":25.1984451277413,"position":2.71121787136203},{"load":25.1216272090248,"position":2.70962564509581},{"load":25.0448087984919,"position":2.70805732624905},{"load":24.9679903758729,"position":2.70648959950928},{"load":24.8911719531758,"position":2.70492187659652},{"load":24.8143535304677,"position":2.70335415422192},{"load":24.7375351047432,"position":2.70178657973391},{"load":24.660716289273,"position":2.70023831042569},{"load":24.5838871002756,"position":2.69934191272505},{"load":24.507056553775,"position":2.69857058580487},{"load":24.430225977206,"position":2.69780225978372},{"load":24.3533954004345,"position":2.69703395400434},{"load":24.276564823663,"position":2.69626564823663},{"load":24.1997342468915,"position":2.69549734246891},{"load":24.1229036701199,"position":2.69472903670119},{"load":24.0460730933484,"position":2.69396073093348},{"load":23.9692425165768,"position":2.69319242516576},{"load":23.8924119398053,"position":2.69242411939691},{"load":23.8155813630401,"position":2.69165581299354},{"load":23.738750788315,"position":2.69088730299318},{"load":23.661920560695,"position":2.6900859935255},{"load":23.5851298281107,"position":2.68749540698424},{"load":23.5083518123118,"position":2.68455192082946},{"load":23.4315741585638,"position":2.68159900603952},{"load":23.3547965075746,"position":2.6786460195221},{"load":23.2780188565876,"position":2.67569303294567},{"load":23.2012412056006,"position":2.67274004636925},{"load":23.1244635546136,"position":2.66978705979283},{"load":23.0476859036266,"position":2.66683407321641},{"load":22.9709082526396,"position":2.66388108663998},{"load":22.8941306016526,"position":2.66092810006347},{"load":22.8173529506691,"position":2.65797511339781},{"load":22.7405753017067,"position":2.65502207420362},{"load":22.663798316199,"position":2.65205226982806},{"load":22.5871409861178,"position":2.64683973413659},{"load":22.510940419767,"position":2.63699080035716},{"load":22.4347598118635,"position":2.62698866057328},{"load":22.3585793811584,"position":2.61698517126303},{"load":22.2823989506585,"position":2.6069816803895},{"load":22.2062185201587,"position":2.59697818951579},{"load":22.1300380896589,"position":2.58697469864207},{"load":22.053857659159,"position":2.57697120776836},{"load":21.9776772286592,"position":2.56696771689464},{"load":21.9014967981594,"position":2.55696422602081},{"load":21.8253163676763,"position":2.54696073501946},{"load":21.7491359483621,"position":2.53695715898188},{"load":21.6729596310398,"position":2.52692288397313},{"load":21.5975354154754,"position":2.5122709618626},{"load":21.5243561493346,"position":2.48885466919192},{"load":21.4512948926339,"position":2.46507273087526},{"load":21.3782352321357,"position":2.44128588937217},{"load":21.3051756068546,"position":2.41749893971173},{"load":21.2321266866855,"position":2.3936793993809},{"load":21.1609481537177,"position":2.36474626079268},{"load":21.0924650067413,"position":2.32990969428521},{"load":21.0241137145648,"position":2.29481512663423},{"load":20.955764348507,"position":2.25971680786729},{"load":20.8874182415272,"position":2.22461215199666},{"load":20.8196519891634,"position":2.188402779711},{"load":20.7626516911751,"position":2.13688100261981},{"load":20.7062643386936,"position":2.08468910381496},{"load":20.6498862533567,"position":2.03248719470853},{"load":20.5935238544127,"position":1.98026840733918},{"load":20.5399065739713,"position":1.92523503756725},{"load":20.4943004044298,"position":1.86339973194336},{"load":20.4491022702065,"position":1.80126555066201},{"load":20.4039104053797,"position":1.73912680933041},{"load":20.3587177341124,"position":1.67698865518843},{"load":20.3131193633393,"position":1.61517249715786},{"load":20.2363247573808,"position":1.61270302056235},{"load":20.1595366885327,"position":1.61537139089844},{"load":20.0827415857306,"position":1.61762280804106},{"load":20.0131508519142,"position":1.58509819553023},{"load":19.979747044381,"position":1.5159048904856},{"load":19.9463348612114,"position":1.446715630168},{"load":19.9126515329581,"position":1.3776580236579},{"load":19.8764642943966,"position":1.30987935653554},{"load":19.8335771129872,"position":1.24612814092703},{"load":19.7906899315779,"position":1.18237692531854},{"load":19.7478027501684,"position":1.11862570971012},{"load":19.7049155687207,"position":1.05487449412743},{"load":19.6620283789458,"position":0.991123284146679},{"load":19.6191400670481,"position":0.927372829138498},{"load":19.5761684181692,"position":0.86367859688309},{"load":19.5306129434558,"position":0.801809056313457},{"load":19.4711167229278,"position":0.753193041830439},{"load":19.4037473326961,"position":0.716248582427329},{"load":19.3363756239699,"position":0.679308352587081},{"load":19.2688498917844,"position":0.642651497708789},{"load":19.1976394708059,"position":0.61382149560719},{"load":19.1209897376778,"position":0.619144889964604},{"load":19.0445576481696,"position":0.626997501900375},{"load":18.9681255586614,"position":0.634850113836148},{"load":18.8916934691533,"position":0.642702725771921},{"load":18.8152613796451,"position":0.650555337707692},{"load":18.7388292901369,"position":0.658407949643463},{"load":18.6623972006287,"position":0.666260561579235},{"load":18.5859651111205,"position":0.674113173515009},{"load":18.5095330216124,"position":0.681965785450781},{"load":18.4331009321042,"position":0.689818397386553},{"load":18.356668842596,"position":0.697671009322325},{"load":18.2802367530878,"position":0.705523621258097},{"load":18.2038046635796,"position":0.713376233193868},{"load":18.1273725740714,"position":0.721228845129512},{"load":18.0509404845599,"position":0.729081457032221},{"load":17.9745083944981,"position":0.736934063579674},{"load":17.8980762512167,"position":0.744786152058931},{"load":17.8216414565577,"position":0.752612373364128},{"load":17.7451599444443,"position":0.759967763356316},{"load":17.6685138050811,"position":0.765343357043862},{"load":17.5918440469496,"position":0.770370882167235},{"load":17.5151742888182,"position":0.775398407290465},{"load":17.4385045306842,"position":0.780425932375747},{"load":17.3618347721305,"position":0.785453451059614},{"load":17.2851649714427,"position":0.790480327042443},{"load":17.2084929960495,"position":0.795473881618056},{"load":17.1317826361723,"position":0.799837425764342},{"load":17.054965482473,"position":0.801466112371154},{"load":16.978142481821,"position":0.802790646864998},{"load":16.9013194811683,"position":0.804115181316155},{"load":16.8244964803868,"position":0.805439708301042},{"load":16.7476734661733,"position":0.806763455310306},{"load":16.6708497370561,"position":0.808044734773877},{"load":16.5940165516698,"position":0.80844906858858},{"load":16.517320769688,"position":0.803840111088996},{"load":16.4412743351583,"position":0.792864337033167},{"load":16.3652279006287,"position":0.781888562977338},{"load":16.289181466099,"position":0.770912788921507},{"load":16.2131350315693,"position":0.759937014865679},{"load":16.1370885970396,"position":0.74896124080985},{"load":16.06104216251,"position":0.737985466754019},{"load":15.9849957279803,"position":0.727009692698192},{"load":15.9089492934506,"position":0.716033918642362},{"load":15.8329028589209,"position":0.705058144586533},{"load":15.7568564243913,"position":0.694082370530704},{"load":15.6808099898616,"position":0.683106596474875},{"load":15.6047635553319,"position":0.672130822419047},{"load":15.5287171208022,"position":0.661155048363216},{"load":15.4526706862726,"position":0.650179274307387},{"load":15.3766242517429,"position":0.639203500251558},{"load":15.3005778172132,"position":0.62822772619573},{"load":15.2245313826835,"position":0.6172519521399},{"load":15.1484849481539,"position":0.606276178084071},{"load":15.0724385136242,"position":0.595300404028242},{"load":14.9963920790945,"position":0.58432462997254},{"load":14.9203456445591,"position":0.573348855956499},{"load":14.844299208894,"position":0.562373089767803},{"load":14.7682526403663,"position":0.551398244295241},{"load":14.6921980124828,"position":0.540479455525195},{"load":14.6159741388529,"position":0.530814613976719},{"load":14.5392019099648,"position":0.527723997463798},{"load":14.462397957431,"position":0.525560505843128},{"load":14.3855940048973,"position":0.52339701422246},{"load":14.3087900523635,"position":0.521233522601856},{"load":14.2319860998292,"position":0.519070031002379},{"load":14.1551821471721,"position":0.516906543759142},{"load":14.078378179438,"position":0.514743592045315},{"load":14.0015732619972,"position":0.512614761922779},{"load":13.924750382243,"position":0.511290423466057},{"load":13.8479895143957,"position":0.51465078642032},{"load":13.7713702855866,"position":0.520397228580999},{"load":13.6947510567776,"position":0.526143670741678},{"load":13.6181318279685,"position":0.531890112902358},{"load":13.5415125991595,"position":0.537636555063037},{"load":13.4648933703504,"position":0.543382997223716},{"load":13.3882741415413,"position":0.549129439384395},{"load":13.3116549127323,"position":0.554875881545074},{"load":13.2350356839232,"position":0.560622323705754},{"load":13.1584164551142,"position":0.566368765866432},{"load":13.0817972263051,"position":0.572115208027112},{"load":13.0051779974961,"position":0.577861650187792},{"load":12.928558768687,"position":0.583608092348472},{"load":12.8519395398779,"position":0.589354534509151},{"load":12.7753203110689,"position":0.595100976669829},{"load":12.6987010822598,"position":0.600847418830509},{"load":12.6220818534508,"position":0.606593860991188},{"load":12.5454626246417,"position":0.612340303151869},{"load":12.4688433958327,"position":0.618086745312547},{"load":12.3922241670236,"position":0.623833187473226},{"load":12.3156049382145,"position":0.629579629633894},{"load":12.2389857094051,"position":0.635326071790086},{"load":12.1623664805146,"position":0.641072512864944},{"load":12.0857472399142,"position":0.646818797796593},{"load":12.0091271212131,"position":0.652553355394125},{"load":11.9324831384443,"position":0.657959246032101},{"load":11.855709893857,"position":0.661024341625339},{"load":11.7788891484738,"position":0.662473789651415},{"load":11.7020684030906,"position":0.663923237669101},{"load":11.6252476576672,"position":0.665372683562166},{"load":11.5484269061769,"position":0.666821807718461},{"load":11.4716056832793,"position":0.668245585668144},{"load":11.394774411402,"position":0.668925821729508},{"load":11.3180857679333,"position":0.664195540743748},{"load":11.2415867914297,"position":0.657023761696535},{"load":11.1650878149261,"position":0.649851982649323},{"load":11.0885888384225,"position":0.64268020360211},{"load":11.0120898619188,"position":0.635508424554896},{"load":10.9355908854153,"position":0.628336645507684},{"load":10.8590919089117,"position":0.621164866460472},{"load":10.7825929324081,"position":0.61399308741326},{"load":10.7060939559045,"position":0.606821308366048},{"load":10.6295949794008,"position":0.599649529318834},{"load":10.5530960028972,"position":0.592477750271621},{"load":10.4765970263937,"position":0.585305971224436},{"load":10.4000980498889,"position":0.578134192189354},{"load":10.3235990730592,"position":0.570962416620198},{"load":10.2471000405083,"position":0.563791235556775},{"load":10.1705960541025,"position":0.556673237320376},{"load":10.0939482053168,"position":0.551333970055871},{"load":10.0176174561137,"position":0.560117154512349},{"load":9.941355957185,"position":0.56948260174921},{"load":9.86509445825629,"position":0.578848048986068},{"load":9.78883295932759,"position":0.588213496222926},{"load":9.71257146039888,"position":0.597578943459786},{"load":9.63630996147016,"position":0.606944390696646},{"load":9.56004846254145,"position":0.616309837933505},{"load":9.48378696361273,"position":0.625675285170365},{"load":9.40752546468401,"position":0.635040732407225},{"load":9.33126396575529,"position":0.644406179643945},{"load":9.25500246682109,"position":0.653771626836138},{"load":9.1787409668061,"position":0.663137065227596},{"load":9.10247934768674,"position":0.672501533640559},{"load":9.02621156632249,"position":0.681815642171663},{"load":8.94981453756447,"position":0.69},{"load":8.87298011936012,"position":0.69},{"load":8.79614570115576,"position":0.689999999999999},{"load":8.71931128295142,"position":0.69},{"load":8.64247686474707,"position":0.689999999999997},{"load":8.56564244654272,"position":0.689999999998487},{"load":8.48880802833838,"position":0.68999999949128},{"load":8.41197361013446,"position":0.68999989758697},{"load":8.3351391932528,"position":0.689989221336393},{"load":8.25830600775231,"position":0.689564877099709},{"load":8.18161252469886,"position":0.684913328283386},{"load":8.10498976836125,"position":0.679214115002076},{"load":8.02836701202362,"position":0.673514901720764},{"load":7.95174425568599,"position":0.667815688439454},{"load":7.87512149934838,"position":0.662116475158144},{"load":7.79849874301075,"position":0.656417261876832},{"load":7.72187598667313,"position":0.650718048595522},{"load":7.6452532303355,"position":0.645018835314211},{"load":7.56863047399787,"position":0.6393196220329},{"load":7.49200771766026,"position":0.633620408751589},{"load":7.41538496132264,"position":0.627921195470318},{"load":7.33876220498385,"position":0.622221982204696},{"load":7.26213944835601,"position":0.616522772825126},{"load":7.18551665163453,"position":0.610824102557016},{"load":7.10889123785791,"position":0.605160770815112},{"load":7.03219798035381,"position":0.600509935924587},{"load":6.95552715120024,"position":0.605521101228741},{"load":6.87885632204668,"position":0.610532266532896},{"load":6.80218549289312,"position":0.61554343183705},{"load":6.72551466373955,"position":0.620554597141205},{"load":6.64884383458598,"position":0.62556576244536},{"load":6.57217300543242,"position":0.630576927749514},{"load":6.49550217627885,"position":0.635588093053669},{"load":6.41883134712529,"position":0.640599258357824},{"load":6.34216051797172,"position":0.645610423661978},{"load":6.26548968881817,"position":0.650621588966132},{"load":6.1888188596646,"position":0.655632754270287},{"load":6.11214803051103,"position":0.660643919574442},{"load":6.03547720135746,"position":0.665655084878596},{"load":5.9588063722039,"position":0.67066625018275},{"load":5.88213554305034,"position":0.675677415486868},{"load":5.80546471389558,"position":0.680688580772924},{"load":5.72879388438904,"position":0.685699740676562},{"load":5.65212299891829,"position":0.690710044142521},{"load":5.57544847652018,"position":0.695664319376044},{"load":5.49870132873304,"position":0.699322079723982},{"load":5.42200484018292,"position":0.694720290410974},{"load":5.34530835163279,"position":0.690118501097959},{"load":5.26861186308291,"position":0.685516711780889},{"load":5.19191537461143,"position":0.680914921157174},{"load":5.11521889945929,"position":0.676312908557416},{"load":5.03852331070388,"position":0.671696150033597},{"load":4.96184570842031,"position":0.666789713894688},{"load":4.88518146632348,"position":0.661678764421565},{"load":4.80851722422664,"position":0.656567814948443},{"load":4.7318529821298,"position":0.65145686547532},{"load":4.65518874003295,"position":0.646345916002196},{"load":4.57852449793611,"position":0.641234966529074},{"load":4.50186025583926,"position":0.636124017055951},{"load":4.42519601374241,"position":0.631013067582827},{"load":4.34853177164558,"position":0.625902118109705},{"load":4.27186752954872,"position":0.620791168636581},{"load":4.19520328745188,"position":0.615680219163459},{"load":4.11853904535504,"position":0.610569269690335},{"load":4.0418748032582,"position":0.605458320217213},{"load":3.96521056116135,"position":0.600347370744117},{"load":3.88854631906318,"position":0.595236421290772},{"load":3.81188207637522,"position":0.590125480684524},{"load":3.73521769178917,"position":0.585016670008492},{"load":3.65853964989501,"position":0.580118152669357},{"load":3.58172506194365,"position":0.581863689001349},{"load":3.50491241294748,"position":0.583692561603128},{"load":3.42809976403009,"position":0.585521437512964},{"load":3.35128713651238,"position":0.587351211381629},{"load":3.27447705459845,"position":0.589284038796219},{"load":3.19787165337673,"position":0.595211754221435},{"load":3.12161852734125,"position":0.604645130638194},{"load":3.04536540130579,"position":0.614078507054953},{"load":2.96911227527033,"position":0.62351188347171},{"load":2.89285914923488,"position":0.632945259888467},{"load":2.81660602319941,"position":0.642378636305226},{"load":2.74035289716395,"position":0.651812012721985},{"load":2.66409977112849,"position":0.661245389138741},{"load":2.58784664509303,"position":0.670678765555498},{"load":2.51159351905727,"position":0.680112141969893},{"load":2.43534039280211,"position":0.689545516610688},{"load":2.35908717138356,"position":0.69897812177868},{"load":2.28281424641094,"position":0.708248827547162},{"load":2.20604839179028,"position":0.705003780608615},{"load":2.12944738568795,"position":0.699019327006872},{"load":2.05284637958562,"position":0.693034873405127},{"load":1.97624537348329,"position":0.687050419803382},{"load":1.89964436738095,"position":0.681065966201782},{"load":1.82304336126523,"position":0.675081512771373},{"load":1.74644234522801,"position":0.669097186360797},{"load":1.66983742102262,"position":0.663163539513861},{"load":1.59301098009295,"position":0.664267963835428},{"load":1.51668298246629,"position":0.673075040484657},{"load":1.44035498483963,"position":0.681882117133887},{"load":1.36402698721298,"position":0.690689193783082},{"load":1.28769898957812,"position":0.699496270361182},{"load":1.21137098128276,"position":0.708303254530733},{"load":1.13503576058335,"position":0.717046667058248},{"load":1.05839244815541,"position":0.711631207808986},{"load":0.982548773066333,"position":0.699332233470338},{"load":0.906705097943898,"position":0.687033259337437},{"load":0.830861390140888,"position":0.674734486752423},{"load":0.755003885290718,"position":0.662521384488833},{"load":0.67817045614353,"position":0.662132053240414},{"load":0.601381677805433,"position":0.664779947147227},{"load":0.52459299193175,"position":0.667430519845626},{"load":0.447821265050753,"position":0.67053257965426},{"load":0.373184387542291,"position":0.688777149711884},{"load":0.298547510033838,"position":0.707021719769506},{"load":0.223910632525376,"position":0.72526628982713},{"load":0.149273755016914,"position":0.743510859884754},{"load":0.0746368775084616,"position":0.761755429942376},{"load":0,"position":0.78}];

	jsonData.forEach(function(item) {
		dinagraphSampleData.labels.push(item.load);
		dinagraphSampleData.datasets[0].data.push(item.position);
	});

	// Create the ChartJS instance using the converted data and the fetch URL.
	var dinagraphSampleChart = new Chart($('#dinagraph_sample_chart'), {
		type: 'line',
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
					}
				}],
				yAxes: [{
					ticks: {
						beginAtZero: true,
						maxTicksLimit: 5,
						stepSize: Math.ceil(10 / 5),
						max: 10
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
