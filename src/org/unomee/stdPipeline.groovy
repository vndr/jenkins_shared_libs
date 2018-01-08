#!/bin/groovy
package org.unomee;
@Grab('org.yaml:snakeyaml:1.17')

import org.yaml.snakeyaml.Yaml

def execute() {

    def pipelineDefinition = [:]
    node {

        stage('Initialize') {
            checkout scm
            echo 'Loading pipeline definition'
            Yaml parser = new Yaml()
            pipelineDefinition = parser.load(new File(pwd() + '/pipeline.yaml').text)
//            List pipelineDefinition = parser.load((new File(pwd) + '/pipeline.yaml').text)
            println("This is array for pipelineDefiniton.pipelineType: " + pipelineDefinition['pipelineType'])
        }

        switch(pipelineDefinition['pipelineType']) {
            case 'python':
                // Instantiate and execute a Python pipeline
                println("I am pythonPipeline cos my pipelineType is:" + pipelineDefinition['pipelineType'])
                println("This is array for pipelineDefiniton: " + pipelineDefinition)
                new pythonPipeline(pipelineDefinition).executePipeline()
//            case 'nodejs':
                // Instantiate and execute a NodeJS pipeline
                // new nodeJSPipeline(pipelineDefinition).executePipeline()
        }

    }

}