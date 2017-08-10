// import { mount } from 'avoriaz'
const AnalyzeInjector = require('!!vue-loader?inject!@/components/Analyze.vue')
import Vue from 'vue'
import pictureMocks from '../mocks/pictures'

function createMockComponent (data) {
  return AnalyzeInjector({
    '@/services/ArticleService': {
      analyze: (text) => {
        if (data === 'error') {
          return Promise.reject('error')
        } else {
          return Promise.resolve(data)
        }
      }
    }
  })
}

function click (vm, selector) {
  const button = vm.$el.querySelector(selector)
  const clickEvent = new window.Event('click')
  button.dispatchEvent(clickEvent)
  vm._watcher.run()
}

describe('Analyze.vue', () => {
  it('renders 1 img in the first column, when 1 picture is returned by the service', (done) => {
    const AnalyzeComponent = createMockComponent(pictureMocks.multiple(1))
    const vm = new Vue(AnalyzeComponent).$mount()
    vm.articleText = 'hello world'

    click(vm, '#analyze-button')

    setTimeout(() => {
      expect(vm.resultsLeft).to.not.be.empty
      expect(vm.$el.querySelectorAll('.pictures-left img').length).to.equal(1)
      done()
    }, 50)
  })

  it('renders 2 imgs in the first 2 columns, when 2 pictures are returned by service', (done) => {
    const AnalyzeComponent = createMockComponent(pictureMocks.multiple(2))
    const vm = new Vue(AnalyzeComponent).$mount()
    vm.articleText = 'hello world'

    click(vm, '#analyze-button')

    setTimeout(() => {
      expect(vm.resultsLeft).to.not.be.empty
      expect(vm.resultsMiddle).to.not.be.empty
      expect(vm.$el.querySelectorAll('.pictures-left img').length).to.equal(1)
      expect(vm.$el.querySelectorAll('.pictures-middle img').length).to.equal(1)
      done()
    }, 50)
  })

  it('renders 3 imgs in each column, when 3 pictures are returned by service', (done) => {
    const AnalyzeComponent = createMockComponent(pictureMocks.multiple(3))
    const vm = new Vue(AnalyzeComponent).$mount()
    vm.articleText = 'hello world'

    click(vm, '#analyze-button')

    setTimeout(() => {
      expect(vm.resultsLeft).to.not.be.empty
      expect(vm.resultsMiddle).to.not.be.empty
      expect(vm.resultsRight).to.not.be.empty
      expect(vm.$el.querySelectorAll('.pictures-left img').length).to.equal(1)
      expect(vm.$el.querySelectorAll('.pictures-middle img').length).to.equal(1)
      expect(vm.$el.querySelectorAll('.pictures-right img').length).to.equal(1)
      done()
    }, 50)
  })

  it('renders 2 img in the first column, 1 image in the middle column, and 1 in the right column, when 4 pictures are returned', (done) => {
    const AnalyzeComponent = createMockComponent(pictureMocks.multiple(4))
    const vm = new Vue(AnalyzeComponent).$mount()
    vm.articleText = 'hello world'

    click(vm, '#analyze-button')

    setTimeout(() => {
      expect(vm.resultsLeft).to.not.be.empty
      expect(vm.resultsMiddle).to.not.be.empty
      expect(vm.resultsRight).to.not.be.empty
      expect(vm.$el.querySelectorAll('.pictures-left img').length).to.equal(2)
      expect(vm.$el.querySelectorAll('.pictures-middle img').length).to.equal(1)
      expect(vm.$el.querySelectorAll('.pictures-right img').length).to.equal(1)
      done()
    }, 50)
  })

  it('renders "not found" text when service returns zero pictures', (done) => {
    const AnalyzeComponent = createMockComponent(pictureMocks.zero)
    const vm = new Vue(AnalyzeComponent).$mount()
    vm.articleText = 'hello world'

    click(vm, '#analyze-button')

    setTimeout(() => {
      expect(vm.results).to.be.empty
      expect(vm.$el.textContent).to.contain('Unfortunately we could not find any pictures for this article')
      done()
    }, 50)
  })

  it('renders "something went wrong" text when service returns rejected promise', (done) => {
    const AnalyzeComponent = createMockComponent('error')
    const vm = new Vue(AnalyzeComponent).$mount()
    vm.articleText = 'hello world'

    click(vm, '#analyze-button')

    setTimeout(() => {
      expect(vm.results).to.be.empty
      expect(vm.$el.textContent).to.contain('Please try again later')
      done()
    }, 50)
  })
})
