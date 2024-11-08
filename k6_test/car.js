import http from 'k6/http'
import { check, sleep } from 'k6'

export const options = {
    scenarios: {
        products: {
            executor: 'constant-arrival-rate',
            duration: '30s',
            rate: 30,
            timeUnit: '1s',
            preAllocatedVUs: 2,
            maxVUs: 50,
        }
    }
}

export default function () {
    // const data = { username: 'username', password: 'password' }
    let res = http.get('http://localhost:8080/breakingbad')

    check(res, { 'success login': (r) => r.status === 200 })

    sleep(0.3)
}
