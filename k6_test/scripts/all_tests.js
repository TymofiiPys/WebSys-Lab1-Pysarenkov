import http from 'k6/http'
import { check, sleep } from 'k6'
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js'

export const options = {
    scenarios: {
        products_car: {
            executor: 'constant-arrival-rate',
            duration: '30s',
            rate: 30,
            timeUnit: '1s',
            preAllocatedVUs: 2,
            maxVUs: 50,
        },
        products_cvu: {
            executor: 'constant-vus',
            vus: 10,
            duration: '30s',
            startTime: '30s',
        },
        products_rvu: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '20s', target: 10 },
                { duration: '10s', target: 0},
            ],
            startTime: '60s',
        }
    }
}

export default function () {
    // const data = { username: 'username', password: 'password' }
    let res = http.get('http://host.docker.internal:8080/breakingbad')

    check(res, { 'successful get' : (r) => r.status === 200 })

    sleep(randomIntBetween(1,5))

    res = http.get('http://host.docker.internal:8080/products/1')

    check(res, { 'successful get' : (r) => r.status === 200 })
}

export function handleSummary(data) {
    return {
        'summary.json': JSON.stringify(data), //the default data object
    };
}