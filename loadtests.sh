cd k6_test
k6 run all_tests.js --out json=tests.js --out influxdb=http://localhost:8086/k6

